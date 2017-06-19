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
			{ path: 'upcoming', loadChildren: './upcoming/upcoming.module#UpcomingModule'},
			{ path: 'trending', loadChildren: './trending/trending.module#TrendingModule'},
			{ path: 'top-rated', loadChildren: './top-rated/top-rated.module#TopRatedModule'},
			{ path: 'sign-in', loadChildren: './sign-in/sign-in.module#SignInModule'},
			{ path: 'favorites', loadChildren: './favorites/favorites.module#FavoritesModule'},
			{ path: 'watchlist', loadChildren: './watchlist/watchlist.module#WatchlistModule'},
			{ path: 'account', loadChildren: './account/account.module#AccountModule'},
			{ path: 'search/:searchQuery', loadChildren: './search/search.module#SearchModule'},
			{ path: 'movie/:id', loadChildren: './movie/movie.module#MovieModule' },
			{ path: '**', component: PageNotFoundComponent }
		]
	}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
