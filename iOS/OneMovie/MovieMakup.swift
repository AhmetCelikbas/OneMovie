//
//  MovieMakup.swift
//  OneMovie
//
//  Created by Ahmet Celikbas on 20/06/2017.
//  Copyright © 2017 Ahmet Celikbas. All rights reserved.
//

import Foundation

class MovieMakup {
    var id: Int = 0
    var title: String
    var poster_path: String
    
    init (id: Int, title: String, poster_path: String) {
        self.id = id
        self.title = title
        
        if(poster_path == "no_poster") {
            self.poster_path = Config.url_no_poster_available
        } else {
            self.poster_path = Config.url_poster_path + poster_path
        }
        
    }
    

}
