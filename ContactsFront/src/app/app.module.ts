import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';



import { ContactService } from './services/contact/contact.service';


import { AppComponent } from './app.component';
import { AboutComponent } from './components/about/about.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ContactComponent } from './components/contact/contact.component';


@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    NavbarComponent,
    ContactComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot([
      {path:'about',component: AboutComponent},
      {path:'contact',component: ContactComponent},
      {path:'',redirectTo: '/about',pathMatch: 'full'}
      ])
  ],
  providers: [ContactService],
  bootstrap: [AppComponent]
})
export class AppModule { }
