import {Component, OnInit} from '@angular/core';
import {StateService} from '../state.service';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-state',
  templateUrl: './state.component.html',
  styleUrls: ['./state.component.css']
})
export class StateComponent implements OnInit {

  _state: string;
  _signals: string;
  interval: any;
  _refreshRate = 2000;
  _id = 'default';

  constructor(private stateService: StateService,
              private route: ActivatedRoute) { }

  ngOnInit() {

    if(this.route.snapshot.paramMap.get('id')) {
      this._id = this.route.snapshot.paramMap.get('id')
    }

    this.getState();
    this.interval = setInterval(() => {
      this.getState();
      this.getSignals();
    }, this._refreshRate);
  };

  getState(): void {
    this.stateService.getState(this._id)
      .subscribe(rules => {this._state = rules});
  }

  getSignals(): void {
    this.stateService.getSignals(this._id)
      .subscribe(signals => {this._signals = signals});
  }

}
