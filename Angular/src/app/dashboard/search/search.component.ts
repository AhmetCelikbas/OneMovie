import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { DataService } from '../../services/data.service';
import 'rxjs/add/operator/switchMap';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
    movies: Object;
    currentPage: number;
    totalPages: number;
    searchQuery: string;

    SWIPE_ACTION = { LEFT: 'swipeleft', RIGHT: 'swiperight' };

    constructor(private data: DataService, private route: ActivatedRoute) {
        this.currentPage = 1;
        this.totalPages = 1;
    }

    ngOnInit() {
      this.route.params.subscribe(params => {
       this.searchQuery = params['searchQuery'];
      });
      this.loadResults();
    }

    loadResults() {
      this.route.params
        .switchMap((params: Params) => this.data.getSearchMovie(params['searchQuery'], this.currentPage))
        .subscribe(data => {
          if (data['total_results'] > 0) {
            this.movies = data;
            if(data['total_pages'] > 1000) {
              this.totalPages = 1000;
            } else {
              this.totalPages = data['total_pages'];
            }
          } else {
            this.movies = {};
            this.totalPages = 0;
          }
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
