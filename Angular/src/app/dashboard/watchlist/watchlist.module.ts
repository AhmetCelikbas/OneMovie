import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WatchlistRoutingModule } from './watchlist-routing.module';
import { WatchlistComponent } from './watchlist.component';
import { ToasterModule } from 'angular2-toaster/angular2-toaster';

@NgModule({
  imports: [
    CommonModule,
    WatchlistRoutingModule,
    ToasterModule
  ],
  declarations: [WatchlistComponent]
})
export class WatchlistModule { }
