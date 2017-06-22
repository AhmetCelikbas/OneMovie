//
//  CastingTableViewCell
//  OneMovie
//
//  Created by Ahmet Celikbas on 21/06/2017.
//  Copyright © 2017 Ahmet Celikbas. All rights reserved.
//

import UIKit

class CastingTableViewCell: UITableViewCell {


    @IBOutlet var CastingRoleLabel: UILabel!
    @IBOutlet var CastingImageView: UIImageView!
    @IBOutlet var CastingActorLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
