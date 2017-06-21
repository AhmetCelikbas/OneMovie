import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';

@Component({
  selector: 'app-trending',
  templateUrl: './trending.component.html',
  styleUrls: ['./trending.component.scss']
})
export class TrendingComponent implements OnInit {
  movies: Object;
  currentPage: number;
  totalPages: number;

  SWIPE_ACTION = { LEFT: 'swipeleft', RIGHT: 'swiperight' };

  constructor(private data: DataService) {
      this.currentPage = 1;
      this.totalPages = 1;
  }

  ngOnInit() {
      this.loadResults();
  }


  loadResults() {
      this.data.getMovieDiscover(this.currentPage).subscribe(response => {
          if(response['total_pages'] > 1000) {
            this.totalPages = 1000;
          } else {
            this.totalPages = response['total_pages'];
          }
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