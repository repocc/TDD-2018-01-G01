import {Component, OnInit} from '@angular/core';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/filter';
import { Injectable } from '@angular/core';

import {StompConfig, StompService} from '@stomp/ng2-stompjs';
import { StompRService } from '@stomp/ng2-stompjs';
import {Message} from '@stomp/stompjs';

@Component({
  selector: 'app-websocket-test',
  templateUrl: './websocket-test.component.html',
  styleUrls: ['./websocket-test.component.css']
})
export class WebsocketTestComponent implements OnInit {

  private url = "ws://localhost:8080/processor/websocket";

  private subscription : any;

  constructor(private _stompService: StompService) { }

  public response(data) {
    console.log(data)
  }

  stomp_subscription = this._stompService.subscribe('/topic/messages');

  createSuscr() {
    this.stomp_subscription.map((message: Message) => {
      return message.body;
    }).subscribe((msg_body: string) => {
      console.log(`Received: ${msg_body}`);
    });
  }

  ngOnInit() {
    this.createSuscr();
  }

}



