import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{

  
  log_in!: FormGroup<any>;
  submitted = false;
  login_response!:String;

  constructor(private formBuilder: FormBuilder,private router:Router, private service:AuthService){}

  ngOnInit() {

    this.log_in = this.formBuilder.group({  
      contact: [null, [Validators.required, Validators.pattern('[0-9]*'), Validators.maxLength(10), Validators.minLength(10), this.firstNumCheck]],
      password: [null, [Validators.required, Validators.maxLength(10)]]
    })
    
  }

  firstNumCheck(control:FormControl) {

    if(control.value != null && control.value[0] != 0){

      return{ notZero:true}

   }
      return null;

  }
  
  
  onSubmit() {
    
    this.submitted = true;
    const contact = this.log_in.get('contact')?.value;
    const pass = this.log_in.get('password')?.value;
  
    if (this.log_in.valid) {
      this.service.login(contact, pass).subscribe(

        (res) => {
          // Handle successful login, e.g., storing token, redirecting, etc.
          console.log('Login successful:', res);

          //store token in a service 
          const token = res;
          this.service.token = token;  
          this.service.isLoggedIn= true;
          this.router.navigate(['/chats'])
          return;
        },
        (error) => {
          // Handle login error, e.g., display error message

          console.error('Login error:', error);
          this.login_response = "incorrect Credentials";
        }  

      );
    }
  }
  

  get f(){ return this.log_in.controls};
}
function jwt_decode(token: any): any {
  throw new Error('Function not implemented.');
}

