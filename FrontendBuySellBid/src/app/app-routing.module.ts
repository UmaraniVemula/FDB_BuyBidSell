import { RouteGuardService } from './service/route-guard.service';
import { WelcomeComponent } from './welcome/welcome.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ErrorComponent } from './error/error.component';
import { BidbuysellComponent } from './bidbuysell/bidbuysell.component';

// welcome 
const routes: Routes = [
  { path: '', component: BidbuysellComponent  },//canActivate, RouteGuardService
  { path: 'displayTopSeller/:rating', component: BidbuysellComponent },
  { path: 'welcome/:name', component: WelcomeComponent, canActivate:[RouteGuardService]},
  { path: '**', component: ErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
