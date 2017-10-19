import { ContactService } from './../../services/contact/contact.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact-new',
  templateUrl: './contact-new.component.html',
  styleUrls: ['./contact-new.component.css']
})
export class ContactNewComponent implements OnInit {

  constructor(private service: ContactService) { }

  ngOnInit() {
  }

onSaveContact(dataForm){
        this.service.saveContact(dataForm)
                    .subscribe(data => {console.log(data)},
                               err=> {JSON.parse(err._body).message}
                               );
}


}
