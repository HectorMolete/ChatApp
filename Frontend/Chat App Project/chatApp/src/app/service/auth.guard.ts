import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService{
  constructor(private authService: AuthService, private router:Router) {}

  canActivate: CanActivateFn = (next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean => {

       // or false get you logged in status from state  
      if (this.authService.LoggedIn()) {
        return true;
      }
      this.router.navigate(["login"]);
      return false;
    }

    /*canActivateChild(
      next: ActivatedRouteSnapshot,
      state: RouterStateSnapshot
    ): boolean {
      return this.canActivate(next, state);
    }*/
  
};
