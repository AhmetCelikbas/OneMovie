import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { ConfigService } from '../../services/config.service';
import { DataService } from '../../services/data.service';
import { AuthenticationService } from '../../services/authentication.service';
import { ToasterService } from 'angular2-toaster/angular2-toaster';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.scss']
})
export class MovieComponent implements OnInit {
  movie: Object;
  videos: Array<Object>;
  similarMovies: Array<Object>;
  cast: Array<Object>;
  url: any;


  constructor(
    private sanitizer: DomSanitizer,
    private route: ActivatedRoute, 
    private toasterService: ToasterService,
    private config: ConfigService, 
    private data: DataService, 
    private authentication: AuthenticationService) 
  {
    this.videos = [];
    this.similarMovies = [];
    this.cast = [];
  }

  ngOnInit() {
    this.route.params
      .switchMap((params: Params) => this.data.getDetailsMovie(+params['id']))
      .subscribe(response => this.movie = response);

    this.route.params
      .switchMap((params: Params) => this.data.getVideoMovie(+params['id']))
      .subscribe(response => {
        this.videos = response.results.slice(0, 3);
        if(this.videos.length > 0) {
          this.seeTrailer(this.videos[0]['key']);
        }
      })

    this.route.params
      .switchMap((params: Params) => this.data.getSimilarMovies(+params['id']))
      .subscribe(response => this.similarMovies = response.results.slice(0,6));

    this.route.params
      .switchMap((params: Params) => this.data.getCastMovie(+params['id']))
      .subscribe(response => this.cast = response.cast.slice(0,6))

  }

  seeTrailer(id: string) {
    this.url = this.sanitizer.bypassSecurityTrustResourceUrl(this.config.Youtube_baseUrl + id);
  }

  favorite(id) {
    this.data.setFavoriteMovie(id, true).then((isFavoriteSetted) => {
      if(isFavoriteSetted) {
        this.toasterService.pop('success', 'Favoris', 'Film ajouté dans les favoris');
      } else {
        this.toasterService.pop('error', 'Favoris', 'Erreur lors de l\'ajout du film dans les favoris');
      }
    });
  }


  watchlist(id) {
    this.data.setWatchlistMovie(id, true).then((isWatchlistSetted) => {
      if(isWatchlistSetted) {
        this.toasterService.pop('success', 'À regarder plus tard', 'Film ajouté dans À regarder plus tard');
      } else {
        this.toasterService.pop('error', 'À regarder plus tard', 'Erreur lors de l\'ajout du film dans À regarder plus tard');
      }
    });
  }

  log() {
    console.log(this.movie);
    console.log(this.videos);
    console.log(this.similarMovies);
    console.log(this.cast);
  }

}
