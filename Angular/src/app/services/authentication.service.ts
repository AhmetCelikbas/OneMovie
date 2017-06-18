import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { ConfigService } from '../services/config.service';

@Injectable()
export class AuthenticationService {

  userData: Object;
  isAuthenticated: Boolean;

  constructor(private http: Http, private config: ConfigService) {
    if(window.localStorage.getItem('session_id')) {
      this.getUserData().then((userData) => {
        if(!userData) {
          this.destroyAuthentication();
        } else {
          this.userData = userData;
          this.isAuthenticated = true;
        }
      });
    } else {
      this.destroyAuthentication();
    }

  }



  destroyAuthentication() {
    this.isAuthenticated = false;
    this.userData = {};
    window.localStorage.removeItem('session_id');
  }


  private getRequestToken() {
    return new Promise((resolve) => {
      this.http.get(this.config.url_requestToken + '?api_key=' + this.config.api_key).subscribe((response) => {
        if (response.json().success === true) {
          resolve(response.json().request_token);
        } else {
          this.destroyAuthentication();
          resolve(false);
        }
      }, (err) => {
        this.destroyAuthentication();
        resolve(false);
      });
    });
  }

  private validateToken(requestToken, usercreds) {
    return new Promise((resolve) => {
      this.http.get(
        this.config.url_validateRequestToken + '?api_key=' + this.config.api_key +
        '&username=' + usercreds.username + '&password=' + usercreds.password +
        '&request_token=' + requestToken).subscribe((response) => {
          if (response.json().success === true) {
            resolve(response.json().request_token);
          } else {
            this.destroyAuthentication();
            resolve(false);
          }
      }, (err) => {
        this.destroyAuthentication();
        resolve(false);
      });
    });
  }

  private getSession_id(validatedRequestToken) {
    return new Promise((resolve) => {
      this.http.get(this.config.url_createSession + '?api_key=' + this.config.api_key + 
        '&request_token=' + validatedRequestToken).subscribe((response) => {
          if (response.json().success === true) {
            resolve(response.json().session_id);
          } else {
            this.destroyAuthentication();
            resolve(false);
          }
      }, (err) => {
        this.destroyAuthentication();
        resolve(false);
      });
    });
  }


  private getUserData() { // this method also check the user authentication
    
      return new Promise((resolve) => {
        this.http.get(this.config.url_userData + '?api_key=' + this.config.api_key + 
          '&session_id=' + window.localStorage.getItem('session_id')).subscribe((data) => {
            if (!data.json().status_code) {
              resolve(data.json());
            } else {
              this.destroyAuthentication();
              resolve(false);
            }
        }, (err) => {
          this.destroyAuthentication();
          resolve(false);
        });
      });
  }


  authenticate(usercreds) {
    return new Promise((resolve) => {
      this.getRequestToken().then((requestToken) => {
        if (!requestToken) {
          this.destroyAuthentication();
          resolve(false);
        } else {
          this.validateToken(requestToken, usercreds).then((validatedRequestToken) => {
            if (!validatedRequestToken) {
              this.destroyAuthentication();
              resolve(false);
            } else {
              this.getSession_id(validatedRequestToken).then((session_id) => {
                if (!session_id) {
                  this.destroyAuthentication();
                  resolve(false);
                } else {
                  window.localStorage.setItem('session_id', session_id.toString());
                  this.getUserData().then((userData) => {
                    if(!userData) {
                      this.destroyAuthentication();
                      resolve(false);
                    } else {
                      this.userData = userData;
                      this.isAuthenticated = true;
                      resolve(true);
                    }
                  });
                }
              });
            }
          });
        }
      });
    });
  }

}
