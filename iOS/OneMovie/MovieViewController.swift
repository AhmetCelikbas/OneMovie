//
//  MovieViewController.swift
//  OneMovie
//
//  Created by Ahmet Celikbas on 20/06/2017.
//  Copyright © 2017 Ahmet Celikbas. All rights reserved.
//

import UIKit

class MovieViewController: UIViewController {

    var movieId  = Int()
    @IBOutlet weak var MovieTitle: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.MovieTitle.text = String(movieId)
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    @IBAction func closeModal(_ sender: UIButton) {
        print("close modal")
        self.dismiss(animated: true) {}
    }
    /*
    @IBAction func closeModal(_ sender: UIButton) {
        //self.dismissViewControllerAnimated(true, completion: {});
        print("close modal")
    }
 */
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
