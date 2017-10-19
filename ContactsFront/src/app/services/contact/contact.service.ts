import { Contact } from './../../model/contact';
import { Injectable} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class ContactService {

  constructor(private http: Http) { }

   getContacts(mc: string,size: number,page: number) {
    let url = "http://localhost:8080/searchContact?mc="+mc+"&size="+size+"&page="+page;
    return this.http.get(url).map(res => res.json());
  }

  saveContact(contact: Contact){

        let url = "http://localhost:8080/addContact";
        return this.http.post(url,contact).map(resp => resp.json());

  }

  getContact(id: number){
    let url = "http://localhost:8080/contact/"+id;
    return this.http.get(url).map(resp => resp.json());
  }

  updateContact(contact: Contact){
    let url = "http://localhost:8080/updateContact/"+contact.id;
    return this.http.put(url,contact).map(resp => resp.json());
  }

  deleteContact(id: number){
    let url = "http://localhost:8080/deleteContact/"+id;
    return this.http.delete(url).map(resp => resp.json());
  }

}
