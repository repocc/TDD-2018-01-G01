import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {WebsocketService} from "./websocket.service";

@Injectable({
  providedIn: 'root'
})
export class StateService {

  private stateUrl = 'http://localhost:8080/get-state';
  private signalsUrl = 'http://localhost:8080/get-signals';
  private postRulesUrl = 'http://localhost:8080/add-rules';
  private dashboardsUrl = 'http://localhost:8080/get-dashboards';
  private createDashboardUrl = 'http://localhost:8080/create-dashboard';

  private socketTest = 'ws://localhost:8080/gs-guide-websocket';


  getState(id): Observable<string> {
    return this.http.get<string>(this.stateUrl  + "/" + id);
  }

  getSignals(id): Observable<string> {
    return this.http.get<string>(this.signalsUrl + "/" + id);
  }

  getDashboardsIds(): Observable<string> {
    return this.http.get<string>(this.dashboardsUrl);
  }

  postRules(rules, id) {
    this.http.post(this.postRulesUrl + "/" + id, rules).subscribe(
      (data) => data,
      (err) => {console.log(err); alert("Error generating rule.\nCheck your syntax or contact the admin.")});
  }

  createDashboard(id) {
    this.http.post(this.createDashboardUrl, id).subscribe(
      (data) => data,
      (err) => {console.log(err); alert("Error generating dashboard.\n" + err.message)});
  }

  public messages: Subject<MessageEvent>;
  connectToSignalsBroker() {
    return this.messages = <Subject<MessageEvent>> this.wsService
      .connect(this.socketTest);
  }


  constructor(private http: HttpClient, private wsService: WebsocketService) { }
}
