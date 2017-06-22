//
//  MovieViewController.swift
//  OneMovie
//
//  Created by Ahmet Celikbas on 20/06/2017.
//  Copyright © 2017 Ahmet Celikbas. All rights reserved.
//

import UIKit
import TMDBSwift
import JHSpinner

class MovieViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {

    var movieId  = Int()
    var TableArray = [CastingMakup]()
    
    @IBOutlet weak var MovieTitle: UILabel!
    @IBOutlet var CastingTableView: UITableView!
    @IBOutlet var MovieGenre: UILabel!
    @IBOutlet var MovieDateAndLikes: UILabel!
    @IBOutlet var MovieImage: UIImageView!
    @IBOutlet var MovieOverview: UITextView!
    @IBOutlet var MovieTrailer: UIWebView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.MovieTitle.text = ""
        self.MovieGenre.text = ""
        self.MovieDateAndLikes.text = ""
        self.MovieOverview.text = ""
        
        self.MovieTrailer.allowsInlineMediaPlayback = true
        self.MovieTrailer.scrollView.isScrollEnabled = false
        self.MovieTrailer.scrollView.bounces = false
        
        self.loadTmdbData()
        
        self.CastingTableView.delegate = self
        self.CastingTableView.dataSource = self
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
 
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        print(TableArray.count)
        return TableArray.count
    }
    
    // Set cell content for each rows of TableArray
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "castingCell", for: indexPath) as! CastingTableViewCell
        
        cell.CastingActorLabel.text = TableArray[indexPath.row].name
        cell.CastingRoleLabel.text = TableArray[indexPath.row].character
        cell.CastingImageView.downloadImage(from: TableArray[indexPath.row].profile_path)

        return cell
    }
    
    
    
    func loadTmdbData() {
        let spinner = JHSpinnerView.showOnView(view, spinnerColor:UIColor.red, overlay:.circular, overlayColor:UIColor.black.withAlphaComponent(0.9))
        self.view.addSubview(spinner)
        
        MovieMDB.movie(Config.apiKey, movieID: self.movieId, language: Config.language){
            apiReturn, movie in
            if let movie = movie{
                self.MovieTitle.text = movie.title ?? "Titre du film non trouvé"
                
                // Get 3 first genre
                var genreCount = 0
                for genre in movie.genres {
                    if(genreCount < 3) {
                        self.MovieGenre.text = self.MovieGenre.text! + genre.name! + " "
                        genreCount += 1
                    }
                }
                
                let dateFormatter = DateFormatter()
                dateFormatter.dateFormat = "yyyy-MM-dd"
                let date = dateFormatter.date(from: movie.release_date!)
                dateFormatter.dateFormat = "dd MMM yyyy"

                self.MovieDateAndLikes.text = dateFormatter.string(from: date!) + " - noté " + String(format:"%.1f", movie.vote_average!)
                
                if((movie.backdrop_path) != nil) {
                     self.MovieImage.downloadImage(from: Config.url_backdrop_path + movie.backdrop_path!)
                }
             
                self.MovieOverview.text = movie.overview ?? ""
             
                MovieMDB.videos(Config.apiKey, movieID: self.movieId, language: Config.language){
                    apiReturn, videos in
                    if let videos = videos{
                        
                        for video in videos {
                            if(video.site == "YouTube") {
                                self.MovieTrailer.loadHTMLString("<iframe width=\"\(self.MovieTrailer.frame.width - 10)\" height=\"\(self.MovieTrailer.frame.height - 10)\" src=\"\(Config.url_youtube_embed + video.key)\" frameborder=\"0\" allowfullscreen></iframe>", baseURL: nil)
  
                                break
                            }
                        }
                    }
                }
               
                
                MovieMDB.credits(Config.apiKey, movieID: self.movieId){
                    apiReturn, credits in
                    if let credits = credits{
                        
                        var castCount = 0
                        for cast in credits.cast{

                            if(castCount <= 6){
                                // Construct a movie object with the current casting data
                                let castingObject = CastingMakup(
                                    name: cast.name ?? "Acteur",
                                    character: cast.character ?? "Rôle",
                                    profile_path: cast.profile_path ?? "no_profile"
                                )
                                
                                // Add casting to the tableArray
                                self.TableArray.append(castingObject)
                                castCount += 1
                            }
                            
                            self.CastingTableView.reloadData()
                            
                            
                        }
                    }
                }
                
                
                
            }
        }
        
        spinner.dismiss()
    }
    
    

    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
