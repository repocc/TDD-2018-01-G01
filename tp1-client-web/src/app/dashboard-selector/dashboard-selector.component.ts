import { Component, OnInit } from '@angular/core';
import {StateService} from "../state.service";

@Component({
  selector: 'app-dashboard-selector',
  templateUrl: './dashboard-selector.component.html',
  styleUrls: ['./dashboard-selector.component.css']
})
export class DashboardSelectorComponent implements OnInit {

  _dashIds = '';

  constructor(private stateService: StateService) { }

  ngOnInit() {
    this.getDashboardList();
  }

  getDashboardList(): void {
    this.stateService.getDashboardsIds()
      .subscribe(dashIds => {this._dashIds = dashIds});
  }

}
