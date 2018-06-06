import {Component, OnInit} from '@angular/core';
import {StateService} from '../state.service';
import {ActivatedRoute} from "@angular/router";

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/filter';
import { Injectable } from '@angular/core';

import {StompConfig, StompService} from '@stomp/ng2-stompjs';
import { StompRService } from '@stomp/ng2-stompjs';
import {Message} from '@stomp/stompjs';

@Component({
  selector: 'app-signal',
  templateUrl: './signal.component.html',
  styleUrls: ['./signal.component.css']
})
export class SignalComponent implements OnInit {

  _state: string;
  _signals: string;
  interval: any;
  _refreshRate = 2000;
  _id = 'default';

  _signalsMap = {};

  constructor(private stateService: StateService,
              private route: ActivatedRoute,
              private _stompService: StompService) { }

  ngOnInit() {

    if(this.route.snapshot.paramMap.get('id')) {
      this._id = this.route.snapshot.paramMap.get('id')
    }

    this.createSuscr();

  };

  getSignals(): void {
    this.stateService.getSignals(this._id)
      .subscribe(signals => {
        this._signals = signals;
        JSON.parse(JSON.stringify(signals)).map(x => this.addToSignalMap(x));
        this.addToSignalMap(signals);
      });
  }

  public addToSignalMap(signal): void {
    console.log("this is your shitty signal");
    console.log(signal);
    console.log((Object.values(signal)[0]));
    console.log((Object.values(signal)[0] == 0));
    var time = new Date().toTimeString().replace(/.*(\d{2}:\d{2}:\d{2}).*/, "$1");
    var elements = this._signalsMap[Object.keys(signal)[0]];
    if(signal && !(Object.values(signal)[0] instanceof Array) && (Object.values(signal)[0])) {

      if(typeof elements === "undefined") {
        this._signalsMap[Object.keys(signal)[0]] = [];
        this._signalsMap[Object.keys(signal)[0]].push([time, Object.values(signal)[0]]);
      } else {
        this._signalsMap[Object.keys(signal)[0]].push([time, Object.values(signal)[0]]);
      }

    }
  }

  public getDataOfMap(signal){
    return this._signalsMap[Object.keys(signal)[0]];
  }

  public getTitle(signal): string {
    return Object.keys(signal)[0];
  }

  stomp_subscription = this._stompService.subscribe('/topic/messages');

  createSuscr() {
    this.stomp_subscription.map((message: Message) => {
      return message.body;
    }).subscribe((msg_body: string) => {
      this.getSignals();
      console.log(`Received: ${msg_body}`);
    });
  }



}




