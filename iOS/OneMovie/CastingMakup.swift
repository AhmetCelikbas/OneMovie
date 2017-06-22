//
//  CastingMakup.swift
//  OneMovie
//
//  Created by Ahmet Celikbas on 20/06/2017.
//  Copyright © 2017 Ahmet Celikbas. All rights reserved.
//

import Foundation

class CastingMakup {
    var name: String
    var character: String
    var profile_path: String
    
    init (name: String, character: String, profile_path: String) {
        self.name = name
        self.character = character
        if(profile_path == "no_profile") {
            self.profile_path = Config.url_no_poster_available
        } else {
            self.profile_path = Config.url_profile_path + profile_path
        }
        
        
    }
    

}
