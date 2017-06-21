import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication.service';
import { DataService } from '../../services/data.service';
import { ToasterService } from 'angular2-toaster/angular2-toaster';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.scss']
})
export class FavoritesComponent implements OnInit {
  movies: Object;
  currentPage: number;
  totalPages: number;

  SWIPE_ACTION = { LEFT: 'swipeleft', RIGHT: 'swiperight' };

  constructor(private data: DataService, public authentication: AuthenticationService, private toasterService: ToasterService,) {
    this.currentPage = 1;
    this.totalPages = 1;
  }

  ngOnInit() {
    this.authentication.getUserData().then((userData) => {
      if(userData) {
        this.loadResults();
      }
    });
  }

  favorite(id) {
    this.data.setFavoriteMovie(id, false).then((isFavoriteSetted) => {
      if(isFavoriteSetted) {
        this.toasterService.pop('success', 'Favoris', 'Film supprimé des favoris');
        this.loadResults();
      } else {
        this.toasterService.pop('error', 'Favoris', 'Erreur lors de la suppression du film des favoris');
      }
    });
  }

  loadResults() {
      this.data.getFavoriteMovie(this.currentPage).subscribe(response => {
        this.totalPages = response['total_pages'];
        this.movies = response;
      });
  }

  setPage(page: number) {
      if (page > this.totalPages || page < 1) {
          return; // out of range
      }
      this.movies = null;
      this.currentPage = page;
      this.loadResults();
  }

  // action triggered when user swipes
  swipe(action = this.SWIPE_ACTION.RIGHT) {
      // swipe right, next 
      if (action === this.SWIPE_ACTION.RIGHT) {
          this.setPage(this.currentPage - 1);
      }

      // swipe left, previous avatar
      if (action === this.SWIPE_ACTION.LEFT) {
          this.setPage(this.currentPage + 1);
      }
  }

}
