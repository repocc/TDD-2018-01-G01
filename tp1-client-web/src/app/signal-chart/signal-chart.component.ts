import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-signal-chart',
  templateUrl: './signal-chart.component.html',
  styleUrls: ['./signal-chart.component.css']
})
export class SignalChartComponent implements OnInit {

  @Input() data: {};
  @Input() signalName: string;

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
    this.getData(this._chart_y_data)
  ];

  public getData(chartYSeries) {
    console.log("thats not my name");
    console.log(this.signalName);
    return {
      data: chartYSeries,
      label: "signal values over time"
    }
  }

  public lineChartLabels:Array<any> = this._chart_x_data;
  public lineChartType:string = 'line';

  public lineChartColors:Array<any> = [
    { // dark grey
      backgroundColor: 'rgba(77,83,96,0.2)',
      borderColor: 'rgba(77,83,96,1)',
      pointBackgroundColor: 'rgba(77,83,96,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(77,83,96,1)'
    }
  ];

  public chartClicked(e:any):void {
    console.log(e);
  }

  public chartHovered(e:any):void {
    console.log(e);
  }


}
