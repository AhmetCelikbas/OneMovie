import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {

  constructor(private router: Router, public authentication: AuthenticationService) { }

  ngOnInit() {
  }

	logout() {
		window.localStorage.removeItem('token');
    this.authentication.destroyAuthentication();
    this.router.navigateByUrl('home');
	}

}
