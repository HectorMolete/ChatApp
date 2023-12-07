import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  token!: string;
  isLoggedIn: Boolean = false;
 
  constructor(private http:HttpClient) { }

  api_post_login__url = "http://localhost:8090/user/login";
  api_post_url = "http://192.168.3.84:8090/user/register";
  api_post_contacts = "http://localhost:8090/user/add-contact";

  register(userData:any){
    
    return this.http.post(this.api_post_url,userData)

  }

  login(contacts: string, password: string): Observable<any> {
    const url = `${this.api_post_login__url}?contacts=${contacts}&password=${password}`;
    
    localStorage.setItem('authToken', this.token);
    return this.http.post(url, {}, { responseType: 'text' });

  }

  LoggedIn(): boolean {
    return !!localStorage.getItem('authToken'); // Return true if the token exists
  }

  addContact(contact: string, username:String){
   
    const data = {
      contact: contact, // Replace 'contacts' with the actual contacts string
      username: username // Replace 'username' with the actual username string
    };

    // Set headers if needed
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
        // Add other headers if required
      })
    };

    return this.http.post(this.api_post_contacts, data, httpOptions);
  }

}
