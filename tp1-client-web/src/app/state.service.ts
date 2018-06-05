import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class StateService {

  private stateUrl = 'http://localhost:8080/get-state';
  private signalsUrl = 'http://localhost:8080/get-signals';
  private postRulesUrl = 'http://localhost:8080/add-rules';
  private dashboardsUrl = 'http://localhost:8080/get-dashboards';
  private createDashboardUrl = 'http://localhost:8080/create-dashboard';


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
      (err) => {console.log(err); alert("Error generating rule.\n" + err.message)});
  }

  createDashboard(id) {
    this.http.post(this.createDashboardUrl, id).subscribe(
      (data) => data,
      (err) => {console.log(err); alert("Error generating dashboard.\n" + err.message)});
  }

  constructor(private http: HttpClient) { }
}
