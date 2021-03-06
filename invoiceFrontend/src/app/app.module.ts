import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbCarouselModule, NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
//Materials

import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import { MatGridListModule } from '@angular/material/grid-list';
import {MatCardModule} from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';
import {MatSelectModule} from '@angular/material/select';

//Components
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RouterModule, Routes } from '@angular/router';
import { UtilisationComponent } from './components/utilisation/utilisation.component';
import { ProjectionComponent } from './components/projection/projection.component';
import { InvoicesComponent } from './components/invoices/invoices.component';
import { TfrComponent } from './components/tfr/tfr.component';
import { IncompleteTimesheetsComponent } from './components/incomplete-timesheets/incomplete-timesheets.component';
import { ExperienceComponent } from './components/experience/experience.component';
import { DiscrepanciesComponent } from './components/discrepancies/discrepancies.component';

import {LoginService} from './services/login.service';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import {CommonServiceService} from './services/common-service.service';
const appRoutes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
{path:'login',component:LoginComponent},
{path:'dashboard',component:DashboardComponent},
{path:'projection',component:ProjectionComponent},
{path:'invoice',component:InvoicesComponent},
{path:'discrepancies',component:DiscrepanciesComponent},
{path:'experience',component:ExperienceComponent},
{path:'followup',component:IncompleteTimesheetsComponent},
{path:'tfr',component:TfrComponent},
{path:'utilisation',component:UtilisationComponent}

]
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UtilisationComponent,
    ProjectionComponent,
    InvoicesComponent,
    TfrComponent,
    IncompleteTimesheetsComponent,
    ExperienceComponent,
    DiscrepanciesComponent,
    DashboardComponent,
    SidebarComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(appRoutes,{useHash:true}),
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    FormsModule,
     ReactiveFormsModule,
     HttpClientModule,
     MatCardModule,
     MatGridListModule,
     MatDividerModule,
     MatSelectModule,
     NgbModule.forRoot(), 
     NgbCarouselModule.forRoot(), NgbAlertModule.forRoot()
     
  ],
  providers: [LoginService,CommonServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
