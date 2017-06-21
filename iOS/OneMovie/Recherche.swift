//
//  ViewController.swift
//  OneMovie
//
//  Created by Ahmet Celikbas on 20/06/2017.
//  Copyright © 2017 Ahmet Celikbas. All rights reserved.
//

import UIKit
import SWRevealViewController
import TMDBSwift
import JHSpinner




class Recherche: UIViewController, UITableViewDelegate, UITableViewDataSource, UISearchBarDelegate {
    
    @IBOutlet var MoviesTableView: UITableView!
    @IBOutlet var searchBar: UISearchBar!
    
    var TableArray = [MovieMakup]()
    var currentPage = Int()
    var total_pages = Int()
    var searchQuery = String()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        self.view.addGestureRecognizer(self.revealViewController().panGestureRecognizer())
        
        self.searchBar.delegate = self
        
        self.currentPage = 1;
        
        //self.loadTmdbData(page: self.currentPage);
        
        self.MoviesTableView.delegate = self
        self.MoviesTableView.dataSource = self
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return TableArray.count
    }
    
    // Set cell content for each rows of TableArray
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "movieCell", for: indexPath) as! MovieTableViewCell
        cell.MovieCellTitle.text = TableArray[indexPath.row].title
        cell.MovieCellImageView.downloadImage(from: TableArray[indexPath.row].poster_path)
        //Set image to here
        return cell
    }
    
    // When a cell is clicked
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        // We perform segue for movie modal
        performSegue(withIdentifier: "movie", sender: TableArray[indexPath.row].id)
    }
    
    
    // Prepare the segue perform to pass movie id to movie modal
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let indexPath = self.MoviesTableView.indexPathForSelectedRow {
            if let destination = segue.destination as? MovieViewController {
                destination.movieId = TableArray[indexPath.row].id
            }
        }
    }
    
    
    // Detect end of scroll to load next page
    func tableView(_ tableView: UITableView, willDisplay cell: UITableViewCell, forRowAt indexPath: IndexPath) {
        // When we reach the scroll end
        if indexPath.row + 1 == TableArray.count {
            // We check if we have another page to load
            if(self.currentPage < self.total_pages) {
                self.currentPage += 1
                loadTmdbData(query: self.searchQuery, page: self.currentPage)
            }
        }
    }
    
    
    @IBAction func scrollToTop(_ sender: UIButton) {
        self.MoviesTableView.setContentOffset(CGPoint.zero, animated: true)
        self.currentPage = 1
        loadTmdbData(query: self.searchQuery, page: self.currentPage)
    }
    
    
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        self.view.endEditing(true)
        self.searchQuery = searchBar.text ?? ""
        self.loadTmdbData(query: self.searchQuery, page: self.currentPage);
        
    }
    
    
    
    func loadTmdbData(query: String, page: Int) {
        
        let spinner = JHSpinnerView.showOnView(view, spinnerColor:UIColor.red, overlay:.circular, overlayColor:UIColor.black.withAlphaComponent(0.9))
        
        self.view.addSubview(spinner)
        
        
        SearchMDB.movie(Config.apiKey, query: query, language: Config.language, page: page, includeAdult: true, year: nil, primaryReleaseYear: nil){
            data, movies in
            
            // Get nomber of pages
            if let total_pages = data.pageResults?.total_pages! {
                self.total_pages = total_pages
            }
            
            // Get nomber of results
            if let total_results = data.pageResults?.total_results! {
                //self.total_pages = total_results
                if(total_results > 0) {
                    
                    self.TableArray = [MovieMakup]()
                    
                    for movie in movies!{
                        //print(movie.id!)
                        //print(movie.title!)
                        //print(Config.url_poster_path + movie.poster_path!)
                        
                        
                        // Construct a movie object with the current movie data
                        let movieObject = MovieMakup(
                            id: movie.id ?? -1,
                            title: movie.title ?? "Titre du film non trouvé.",
                            poster_path: movie.poster_path ?? "no_poster"
                        )
                        
                        // Add movie to the tableArray
                        self.TableArray.append(movieObject)
                        
                        
                    }
                } else {
                    self.TableArray = [MovieMakup]()
                }
            }
            
    
                
            // Refresh MoviesTableView when data is fetched
            self.MoviesTableView.reloadData()
            spinner.dismiss()
        }
        

        
    }
}

