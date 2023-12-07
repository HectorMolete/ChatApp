import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';


@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent {

  register!: FormGroup;
  submitted = false;

  
  constructor(private formBuilder: FormBuilder,private router:Router, private service:AuthService) { }

  ngOnInit() {

    this.register = this.formBuilder.group({
      username: [null, [Validators.required, Validators.pattern('[a-zA-Z]*'), Validators.maxLength(30), Validators.minLength(4)]],
      contact: [null, [Validators.required, Validators.pattern('[0-9]*'), Validators.maxLength(10), Validators.minLength(10), this.firstNumCheck]],
      password: [null, [Validators.required, Validators.maxLength(10),this.passCheck]],
      confirmPassword: [null, [Validators.required, Validators.maxLength(10),this.pass]]
    })
    
  }

  passCheck(control:FormControl){

    if(control.root.get('confirmPassword')?.value != null){

      let confirmPassword = control.root.get('confirmPassword')?.value
      let password = control.value;
      console.log(confirmPassword)

      if(confirmPassword !== password){

        return {noMatch:true}
      }

    }
    return null;
  }


  pass(control:FormControl){
    if(control.value != null){

      let confirmPas = control.value;
      let password = control.root.get('password')?.value;

      if(password !== confirmPas)
      {
        return {noMatch:true}
      }
    }

    return null;

  }

  firstNumCheck(control:FormControl) {

    if(control.value != null && control.value[0] != 0){

      return{ notZero:true}

   }
      return null;

  }

  get f() {return this.register.controls;}

  onSubmit() {
    this.submitted = true;

      if(this.register.valid)
        {
            
             this.service.register(this.register.value).subscribe(res=>{

              this.router.navigate(["/chats"]);

             }),
             console.log(this.register.controls);
             
        } 
  } 
  
}



