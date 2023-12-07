import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { WebSocketService } from '../service/web-socket.service';
import { Message } from '@stomp/stompjs';
import { AuthService } from '../service/auth.service';
import {jwtDecode } from 'jwt-decode';


@Component({
  selector: 'app-chats-page',
  templateUrl: './chats-page.component.html',
  styleUrls: ['./chats-page.component.css']
})
export class ChatsPageComponent implements OnInit{

  register!: FormGroup;
  saveInfo = false;  
  closeResult: string = '';
  messageToSend!: string;
  receivedMessages: string[] = [];
  username!: String;
  
 


 
  /*------------------------------------------
  --------------------------------------------
  Created constructor
  --------------------------------------------
  --------------------------------------------*/
  constructor(private modalService: NgbModal, private formBuilder: FormBuilder, private webSocketService: WebSocketService,private service:AuthService) {

  }
 
  ngOnInit() {

    const token = this.service.token; // Replace this with your method to retrieve the JWT token

     console.log(token)

    if (token) {
      try {
        // Decode the JWT token to extract user information
        const decodedToken: any = jwtDecode(token);

        // Extract the username from the decoded token
        this.username = decodedToken.sub; // Replace 'username' with the actual property in your JWT token
        console.log('Username:', this.username);
        
        // You can now use this.username as needed in your application
      } catch (error) {
        console.error('Error decoding JWT token:', error);
        // Handle error if decoding fails (e.g., invalid token format)
      }
    }
    this.register = this.formBuilder.group({
      username: [null, [Validators.required, Validators.pattern('[a-zA-Z]*'), Validators.maxLength(30), Validators.minLength(4)]],
      contact: [null, [Validators.required, Validators.pattern('[0-9]*'), Validators.maxLength(10), Validators.minLength(10), this.firstNumCheck]]
    })

    this.webSocketService.connect();

    // Subscribe to a destination to receive messages
    this.webSocketService.subscribe('/topic/messages', (message: Message) => {

      this.receivedMessages= [message.body];
      console.log('Received message:', message.body,message.headers);
      // Handle the received message here
    });


  
  }
  



  firstNumCheck(control:FormControl) {

    if(control.value != null && control.value[0] != 0){

      return{ notZero:true}

   }
      return null;

  }
    
  /**
   * Write code on Method
   *
   * @return response()
   */
  open(content:any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result: any) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason: any) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  } 
    
  /**
   * Write code on Method
   *
   * @return response()
   */


  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  save(){

    this.saveInfo = true;
    console.log(this.register.controls)

    const contact = this.register.value.contact;
    const username = this.register.value.username;
   

    if(this.register.valid)
    {
      console.log(contact);
      console.log(username);

         this.service.addContact(contact,username).subscribe(
          (res) => {
            console.log('Response:', res);
            
            // Handle the response data if needed
          },
          (error) => {

            console.log("im here");
           
              console.error(
                `Backend returned code ${error.status}, ` +
                `body was: ${error.error.message}`
              );
            }
            // Handle the error or display a suitable message
          
        );
        
         
    } 

  }

  get f() {return this.register.controls}

  sendMessage(): void {
    // Send a message to a specific destination
    this.webSocketService.sendMessage('/topic/messages', JSON.stringify(this.messageToSend));
  }

  ngOnDestroy(): void {
    this.webSocketService.disconnect();
  }


}
