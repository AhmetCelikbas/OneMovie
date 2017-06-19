import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  searchQuery: string;

  constructor(private router: Router, public authentication: AuthenticationService) { }

  ngOnInit() {
  }

  search() {
    if (this.searchQuery === '') {
        this.router.navigate(['/index']);
    } else {
        this.router.navigate(['/search', this.searchQuery]);
    }
    this.searchQuery = '';
  }

}
