import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MovieRoutingModule } from './movie-routing.module';
import { MovieComponent } from './movie.component';
import { ToasterModule } from 'angular2-toaster/angular2-toaster';

@NgModule({
  imports: [
    CommonModule,
    MovieRoutingModule,
    ToasterModule
  ],
  declarations: [MovieComponent]
})
export class MovieModule { }
