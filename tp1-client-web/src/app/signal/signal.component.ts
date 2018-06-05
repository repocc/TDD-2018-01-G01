import {Component, OnInit} from '@angular/core';
import {StateService} from '../state.service';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-signal',
  templateUrl: './signal.component.html',
  styleUrls: ['./signal.component.css']
})
export class SignalComponent implements OnInit {

  _state: string;
  _signals: string;
  interval: any;
  _refreshRate = 7000;
  _id = 'default';

  _signalsMap = {};

  constructor(private stateService: StateService,
              private route: ActivatedRoute) { }

  ngOnInit() {

    if(this.route.snapshot.paramMap.get('id')) {
      this._id = this.route.snapshot.paramMap.get('id')
    }

    this.interval = setInterval(() => {
      this.getSignals();
    }, this._refreshRate);
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
    var time = new Date().toTimeString().replace(/.*(\d{2}:\d{2}:\d{2}).*/, "$1");
    var elements = this._signalsMap[Object.keys(signal)[0]];
    if(signal && (Object.keys(signal)[0] != "0")) {
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


}




