//
//  config.swift
//  OneMovie
//
//  Created by Ahmet Celikbas on 20/06/2017.
//  Copyright © 2017 Ahmet Celikbas. All rights reserved.
//

import Foundation




struct Config {
    static let apiKey = "d8e80ec6dd23ba1687c149969994f760"
    static let language = "fr"
    
    static let url_poster_path = "https://image.tmdb.org/t/p/w500/"
    static let url_no_poster_available = "http://ddbu.billiardport.com/img/posters/no_poster_available.jpg"
    
    static let url_discover = "https://api.themoviedb.org/3/discover/movie"
    static let url_search = "https://api.themoviedb.org/3/search/movie"
    static let url_movie = "https://api.themoviedb.org/3/movie/"
    static let url_person = "https://api.themoviedb.org/3/person/"
    static let url_genre = "https://api.themoviedb.org/3/genre/"
    static let url_account = "https://api.themoviedb.org/3/account/"
    
    static let url_requestToken = "https://api.themoviedb.org/3/authentication/token/new"
    static let url_validateRequestToken = "https://api.themoviedb.org/3/authentication/token/validate_with_login"
    static let url_createSession = "https://api.themoviedb.org/3/authentication/session/new"
    static let url_userData = "https://api.themoviedb.org/3/account"
}
