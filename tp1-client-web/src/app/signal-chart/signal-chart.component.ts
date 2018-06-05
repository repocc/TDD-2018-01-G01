import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-signal-chart',
  templateUrl: './signal-chart.component.html',
  styleUrls: ['./signal-chart.component.css']
})
export class SignalChartComponent implements OnInit {

  @Input() data: {};

  _chart_x_data = [];
  _chart_y_data = [];

  constructor() { }

  ngOnInit() {
  console.log(this.data[0]);
    Object.values(this.data).forEach(d => {
      this._chart_x_data.push(d[0]);
      this._chart_y_data.push(d[1]);
    })
  }

  // lineChart
  public signalsData:Array<any> = [
    this._chart_y_data
  ];

  public lineChartLabels:Array<any> = this._chart_x_data;
  public lineChartType:string = 'line';

  public chartClicked(e:any):void {
    console.log(e);
  }

  public chartHovered(e:any):void {
    console.log(e);
  }


}
