import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { PageNotFoundComponent } from './not-found.component';

const routes: Routes = [
	{
		path: '', component: DashboardComponent,
		children: [
			{ path: '', loadChildren: './home/home.module#HomeModule'},
			{ path: 'home', loadChildren: './home/home.module#HomeModule'},
			{ path: 'home', loadChildren: './home/home.module#HomeModule'},
			{ path: 'search/:searchQuery', loadChildren: './search/search.module#SearchModule'},
			{ path: '**', component: PageNotFoundComponent }
		]
	}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
