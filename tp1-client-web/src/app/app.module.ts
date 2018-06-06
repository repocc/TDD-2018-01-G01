import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { StateComponent } from './state/state.component';
import { RulesDefinitionComponent } from './rules-definition/rules-definition.component';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { AppRoutingModule } from './/app-routing.module';
import { HomeComponent } from './home/home.component';
import { DashboardSelectorComponent } from './dashboard-selector/dashboard-selector.component';
import { SignalComponent } from './signal/signal.component';
import { ChartsModule } from 'ng2-charts';
import { SignalChartComponent } from './signal-chart/signal-chart.component';
import { NgModule } from "@angular/core";
import { WebsocketTestComponent } from './websocket-test/websocket-test.component';
import {StompConfig, StompService} from "@stomp/ng2-stompjs";

const stompConfig: StompConfig = {
  // Which server?
  url: 'ws://localhost:8080/processor/websocket',

  // Headers
  // Typical keys: login, passcode, host
  headers: {
    login: 'guest',
    passcode: 'guest'
  },

  // How often to heartbeat?
  // Interval in milliseconds, set to 0 to disable
  heartbeat_in: 0, // Typical value 0 - disabled
  heartbeat_out: 20000, // Typical value 20000 - every 20 seconds
  // Wait in milliseconds before attempting auto reconnect
  // Set to 0 to disable
  // Typical value 5000 (5 seconds)
  reconnect_delay: 5000,

  // Will log diagnostics on console
  debug: true

};


@NgModule({
  declarations: [
    AppComponent,
    StateComponent,
    RulesDefinitionComponent,
    HeaderComponent,
    HomeComponent,
    DashboardSelectorComponent,
    SignalComponent,
    SignalChartComponent,
    WebsocketTestComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    ChartsModule
  ],
  providers: [
    StompService,
    {
      provide: StompConfig,
      useValue: stompConfig
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
