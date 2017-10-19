import { ContactService } from './../../services/contact/contact.service';
import { Contact } from './../../model/contact';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'new-contact',
  templateUrl: './new-contact.component.html',
  styleUrls: ['./new-contact.component.css']
})
export class NewContactComponent implements OnInit {
  contact: Contact=new Contact();
  mode: number=1;
  constructor(private service: ContactService) { }

  ngOnInit() {
  }

  save()
  {
    this.service.saveContact(this.contact)
                .subscribe(data => {console.log(data); this.mode=2;},err => {console.log(err)})

              

  }

}
