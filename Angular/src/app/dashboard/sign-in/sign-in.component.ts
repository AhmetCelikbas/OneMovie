import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {
  usercreds = {
    username: '',
    password: ''
  }
  errorMsg: string;
  loginAttemptFinished: Boolean;

  constructor(private router: Router, private authentication: AuthenticationService) {
    this.loginAttemptFinished = true;
  }

  ngOnInit() {
  }


  login() {
    this.loginAttemptFinished = false;
    this.authentication.authenticate(this.usercreds).then((isAuthenticated) => {
      if (isAuthenticated) {
        this.errorMsg = '';
        this.router.navigate(['/home']);
      } else {
        this.errorMsg = 'Nom d\'utilisateur ou mot de passe erroné.';
      }
      this.loginAttemptFinished = true;
    });
  }

}
