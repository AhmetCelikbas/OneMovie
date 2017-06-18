import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';

import { ConfigService } from './config.service';

@Injectable()
export class DataService {

  constructor(
    private http: Http,
    private config: ConfigService
    ) { }


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

}
