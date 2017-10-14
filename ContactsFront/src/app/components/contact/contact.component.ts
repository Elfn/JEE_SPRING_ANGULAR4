import { ContactService } from './../../services/contact/contact.service';
import { Component, OnInit } from '@angular/core';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
message:any;
  constructor(private service: ContactService) { }

  ngOnInit() {

    this.service.getContacts()
                .map(res => res.json())
                .subscribe(data => this.message = data, err => console.log(err));

  }

}
