import { Injectable} from '@angular/core';
import {Http} from '@angular/http';

@Injectable()
export class ContactService {

  constructor(private http: Http) { }

   getContacts() {
    let url = "http://localhost:8080/contacts";
    return this.http.get(url);
  }

}
