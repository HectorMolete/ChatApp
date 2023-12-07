import { Injectable } from '@angular/core';
import { Client, Message } from '@stomp/stompjs';



@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  private client: Client;

  constructor() {
    this.client = new Client({
      brokerURL: 'ws://localhost:8090/ws', // Replace with your WebSocket endpoint
      debug: (str) => {
        console.log(str);
      }
    });
  }

  public connect(): void {
    this.client.activate();
  }

  public disconnect(): void {
    this.client.deactivate();
  }

  public subscribe(destination: string, callback: (message: Message) => void): void {
    this.client.onConnect = () => {
      this.client.subscribe(destination, callback);
    };
  }

  public sendMessage(destination: string, body: string): void {
    this.client.publish({ destination, body });
  }
  
}