import { Injectable } from '@angular/core';

@Injectable()
export class ConfigService {

    api_key = 'd8e80ec6dd23ba1687c149969994f760';
    language = 'fr';
    url_discover = 'https://api.themoviedb.org/3/discover/movie';
    url_search = 'https://api.themoviedb.org/3/search/movie';
    url_movie = 'https://api.themoviedb.org/3/movie/';
    url_person = 'https://api.themoviedb.org/3/person/';
    url_genre = 'https://api.themoviedb.org/3/genre/';

    url_requestToken = 'https://api.themoviedb.org/3/authentication/token/new';
    url_validateRequestToken = 'https://api.themoviedb.org/3/authentication/token/validate_with_login';
    url_createSession = 'https://api.themoviedb.org/3/authentication/session/new';
    url_userData = 'https://api.themoviedb.org/3/account';

  constructor() { }

}
