import { ContactService } from './../../services/contact/contact.service';
import { Contact } from './../../model/contact';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-contact',
  templateUrl: './edit-contact.component.html',
  styleUrls: ['./edit-contact.component.css']
})
export class EditContactComponent implements OnInit {
contact: Contact=new Contact();
idContact:number;
  constructor(public activedRoute: ActivatedRoute,private service: ContactService, private router: Router) { this.idContact= activedRoute.snapshot.params['id']}

  ngOnInit() {
    this.service.getContact(this.idContact).subscribe(data => this.contact=data);
  }

  onUpdateContact()
  {
   this.service.updateContact(this.contact).subscribe(data => {this.contact=data; alert('updated!!'); this.router.navigate(['/contact'])},
                                                      err => console.log(err));
  }

}
