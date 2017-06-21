//
//  MovieTableViewCell.swift
//  OneMovie
//
//  Created by Ahmet Celikbas on 21/06/2017.
//  Copyright © 2017 Ahmet Celikbas. All rights reserved.
//

import UIKit

class MovieTableViewCell: UITableViewCell {

    @IBOutlet var MovieCellImageView: UIImageView!
    @IBOutlet var MovieCellTitle: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
