import { Component, OnInit } from '@angular/core';
import {StateService} from "../state.service";

@Component({
  selector: 'app-rules-definition',
  templateUrl: './rules-definition.component.html',
  styleUrls: ['./rules-definition.component.css']
})
export class RulesDefinitionComponent implements OnInit {

  _ruleCounter = '[["define-counter","spam-count",[],["current","spam"]]]';
  _ruleSignal = '[["define-signal",{"spam-fraction":["\\/",["counter-value","spam-count",[]],["counter-value","email-count",[]]]},true]]';
  _newDashboard = '';
  _toDashboard = 'default';
  constructor(private stateService: StateService) { }

  public addRuleCounter() {
    this.stateService.postRules(this._ruleCounter, this._toDashboard);
  }

  public addRuleSignal() {
    this.stateService.postRules(this._ruleSignal, this._toDashboard);
  }

  public createDashboard() {
    this.stateService.createDashboard(this._newDashboard);
  }

  ngOnInit() {
  }

}
