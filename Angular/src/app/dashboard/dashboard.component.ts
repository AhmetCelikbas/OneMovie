import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  searchQuery: string;

  constructor() { }

  ngOnInit() {
  }

  // onKey(event: any) {
  //   this.searchQuery = event.target.value;
  //   console.log(this.searchQuery);
  // }

  search() {
    console.log(this.searchQuery);
  }

}
