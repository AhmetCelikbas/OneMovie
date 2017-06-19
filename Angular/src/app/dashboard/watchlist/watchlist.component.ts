import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication.service';
import { DataService } from '../../services/data.service';
import { ToasterService } from 'angular2-toaster/angular2-toaster';

@Component({
  selector: 'app-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.scss']
})
export class WatchlistComponent implements OnInit {
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

  watchlist(id) {
    this.data.setWatchlistMovie(id, false).then((isWatchlistSetted) => {
      if(isWatchlistSetted) {
        this.toasterService.pop('success', 'À regarder plus tard', 'Film supprimé de À regarder plus tard');
        this.loadResults();
      } else {
        this.toasterService.pop('error', 'À regarder plus tard', 'Erreur lors de la suppression du film de À regarder plus tard');
      }
    });
  }

  loadResults() {
      this.data.getWatchlistMovie(this.currentPage).subscribe(response => {
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
