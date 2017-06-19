import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';

import { ConfigService } from './config.service';
import { AuthenticationService } from './authentication.service';

@Injectable()
export class DataService {

  constructor(
    private http: Http,
    private config: ConfigService,
    private authentication: AuthenticationService
    ) { }

  private setRequestOptions(): RequestOptions {
      return new RequestOptions({
          headers: new Headers({
            'Content-Type': 'application/json;charset=utf-8'
          })
      });
  }


  getNowPlaying(page: number) {
    return this.http.get(`${this.config.url_movie}now_playing?api_key=${this.config.api_key}&language=${this.config.language}&page=${page}`)
        .map((res: Response) => res.json());
  }

  getSearchMovie(name: string, page: number) {
    return this.http.get(`${this.config.url_search}?api_key=${this.config.api_key}&language=${this.config.language}&query=${name}&page=${page}`)
        .map(res => res.json());
  }

  getUpComing(page: number) {
    return this.http.get(`${this.config.url_movie}upcoming?api_key=${this.config.api_key}&language=${this.config.language}&page=${page}`)
        .map((res: Response) => res.json());
  }

  getMovieDiscover(page: number) {
    return this.http.get(`${this.config.url_discover}?api_key=${this.config.api_key}&language=${this.config.language}&sort_by=popularity.desc&page=${page}`)
        .map((res: Response) => res.json())
  }

  getTopRated(page: number) {
    return this.http.get(`${this.config.url_movie}top_rated?api_key=${this.config.api_key}&language=${this.config.language}&page=${page}`)
        .map((res: Response) => res.json())
  }

  getDetailsMovie(code: number) {
    return this.http.get(`${this.config.url_movie}${code}?api_key=${this.config.api_key}&language=${this.config.language}`)
        .map((res: Response) => res.json());
  }

  getSimilarMovies(code: number) {
      return this.http.get(`${this.config.url_movie}${code}/similar?api_key=${this.config.api_key}&language=${this.config.language}`)
          .map((res: Response) => res.json())
  }

  getCastMovie(code: number) {
      return this.http.get(`${this.config.url_movie}${code}/credits?api_key=${this.config.api_key}`)
          .map((res: Response) => res.json())
  }
  getVideoMovie(code: number) {
      return this.http.get(`${this.config.url_movie}${code}/videos?api_key=${this.config.api_key}&language=${this.config.language}`)
          .map((res: Response) => res.json())
  }

  getFavoriteMovie(page: number) {
    return this.http.get(
      this.config.url_account + this.authentication.userData['id'] + 
      '/favorite/movies?api_key=' + this.config.api_key + '&language=' + this.config.language +
      '&session_id=' + window.localStorage.getItem('session_id') + '&page=' + page)
          .map((res: Response) => res.json().results );
  }




  setFavoriteMovie(code: number, favorite: boolean) {
    if(this.authentication.isAuthenticated) {
      let postData = {
        "media_type": "movie",
        "media_id": code,
        "favorite": favorite
      };

      return new Promise((resolve) => {
          this.http.post(
            this.config.url_account + 
            this.authentication.userData['id'] + 
            '/favorite?api_key=' + this.config.api_key + 
            '&session_id=' + window.localStorage.getItem('session_id'),
            postData,
            {headers: new Headers}
          ).subscribe((response) => {
            if (response.json().status_code === 1 || response.json().status_code === 12 || response.json().status_code === 13) {
              resolve(true);
            } else {
              resolve(false);
            }
          }, (err) => {
            resolve(false);
          });
        });
    }
  }



  getWatchlistMovie(page: number) {
    return this.http.get(
      this.config.url_account + this.authentication.userData['id'] + 
      '/watchlist/movies?api_key=' + this.config.api_key + '&language=' + this.config.language +
      '&session_id=' + window.localStorage.getItem('session_id') + '&page=' + page)
          .map((res: Response) => res.json().results );
  }


  setWatchlistMovie(code: number, watchlist: boolean) {
    if(this.authentication.isAuthenticated) {
      let postData = {
        "media_type": "movie",
        "media_id": code,
        "watchlist": watchlist
      };

      return new Promise((resolve) => {
          this.http.post(
            this.config.url_account + 
            this.authentication.userData['id'] + 
            '/watchlist?api_key=' + this.config.api_key + 
            '&session_id=' + window.localStorage.getItem('session_id'),
            postData,
            {headers: new Headers}
          ).subscribe((response) => {
            if (response.json().status_code === 1 || response.json().status_code === 12 || response.json().status_code === 13) {
              resolve(true);
            } else {
              resolve(false);
            }
          }, (err) => {
            resolve(false);
          });
        });
    }
  }

}