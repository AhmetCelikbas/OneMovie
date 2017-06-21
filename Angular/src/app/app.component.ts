import { Component } from '@angular/core';

import {
    Router,
    // import as RouterEvent to avoid confusion with the DOM Event
    Event as RouterEvent,
    NavigationStart,
    NavigationEnd,
    NavigationCancel,
    NavigationError
} from '@angular/router'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
    // Sets initial value to true to show loading spinner on first load
    loading: boolean;
    process;

    constructor(private router: Router) {
      this.loading = true;
      this.router.events.subscribe((event: RouterEvent) => {
          this.navigationInterceptor(event);
      });
    }

    // Shows and hides the loading spinner during RouterEvent changes
    navigationInterceptor(event: RouterEvent): void {
        if (event instanceof NavigationStart) {
            this.process = 20;
            this.loading = true;
        }
        if (event instanceof NavigationEnd) {
            this.process = 100;
            this.loading = false;
        }

        // Set loading state to false in both of the below events to hide the spinner in case a request fails
        if (event instanceof NavigationCancel) {
            this.process = 100;
            this.loading = false;
        }
        if (event instanceof NavigationError) {
            this.process = 100;
            this.loading = false;
        }
    }
}
