import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FavoritesRoutingModule } from './favorites-routing.module';
import { FavoritesComponent } from './favorites.component';
import { ToasterModule } from 'angular2-toaster/angular2-toaster';

@NgModule({
  imports: [
    CommonModule,
    FavoritesRoutingModule,
    ToasterModule
  ],
  declarations: [FavoritesComponent]
})
export class FavoritesModule { }
