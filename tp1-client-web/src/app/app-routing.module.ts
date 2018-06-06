import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {StateComponent} from "./state/state.component";
import {RulesDefinitionComponent} from "./rules-definition/rules-definition.component";
import {HomeComponent} from "./home/home.component";
import {DashboardSelectorComponent} from "./dashboard-selector/dashboard-selector.component";
import {SignalComponent} from "./signal/signal.component";
import {WebsocketTestComponent} from "./websocket-test/websocket-test.component";

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'state', component: StateComponent },
  { path: 'definitions', component: RulesDefinitionComponent },
  { path: 'dashboard-selector', component: DashboardSelectorComponent },
  { path: 'state/:id', component: StateComponent },
  { path: 'signals/:id', component: SignalComponent },
  { path: 'test', component: WebsocketTestComponent },
  { path: '', component: HomeComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
