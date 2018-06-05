import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

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
import { SignalChartComponent } from './signal-chart/signal-chart.component'; // <- HERE

@NgModule({
  declarations: [
    AppComponent,
    StateComponent,
    RulesDefinitionComponent,
    HeaderComponent,
    HomeComponent,
    DashboardSelectorComponent,
    SignalComponent,
    SignalChartComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    ChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
