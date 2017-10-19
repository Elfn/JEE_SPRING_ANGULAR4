import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';
import { BsDropdownModule } from 'ngx-bootstrap';
import { FormsModule } from '@angular/forms';



import { ContactService } from './services/contact/contact.service';


import { AppComponent } from './app.component';
import { AboutComponent } from './components/about/about.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ContactComponent } from './components/contact/contact.component';
import { NewContactComponent } from './components/new-contact/new-contact.component';
import { ContactNewComponent } from './components/contact-new/contact-new.component';
import { EditContactComponent } from './components/edit-contact/edit-contact.component';


@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    NavbarComponent,
    ContactComponent,
    NewContactComponent,
    ContactNewComponent,
    EditContactComponent,
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
     RouterModule.forRoot([
     {path:'about',component: AboutComponent},
    {path:'contact',component: ContactComponent},
    {path:'new-contact',component: ContactNewComponent},
    {path:'editContact/:id',component: EditContactComponent},
      {path:'',redirectTo: '/about',pathMatch: 'full'}
      ])
      //  BsDropdownModule.forRoot([
      // {path:'about',component: AboutComponent},
      // {path:'contact',component: ContactComponent},
      // {path:'',redirectTo: '/about',pathMatch: 'full'}
      // ])
  ],
  providers: [ContactService],
  bootstrap: [AppComponent]
})
export class AppModule { }
