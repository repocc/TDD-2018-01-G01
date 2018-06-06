import {Component, OnInit} from '@angular/core';
import {StateService} from '../state.service';
import {ActivatedRoute} from "@angular/router";

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/filter';

import {StompService} from '@stomp/ng2-stompjs';
import {Message} from '@stomp/stompjs';

@Component({
  selector: 'app-signal',
  templateUrl: './signal.component.html',
  styleUrls: ['./signal.component.css']
})
export class SignalComponent implements OnInit {

  private _signals: string;
  interval: any;
  private _id = 'default';
  private _signalsMap = {};

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
      });
  }

  public addToSignalMap(signal): void {

    var time = new Date().toTimeString().replace(/.*(\d{2}:\d{2}:\d{2}).*/, "$1");
    var elements = this._signalsMap[Object.keys(signal)[0]];
    if(this.isValidPoint(signal)) {

      if(typeof elements === "undefined") {
        this._signalsMap[Object.keys(signal)[0]] = [];
        this._signalsMap[Object.keys(signal)[0]].push([time, Object.values(signal)[0]]);
      } else {
        this._signalsMap[Object.keys(signal)[0]].push([time, Object.values(signal)[0]]);
      }

    }
  }

  public isValidPoint (signal) {
    return (signal && !(Object.values(signal)[0] instanceof Array) && (Object.values(signal)[0]));
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




