import { Router } from '@angular/router';
import { HttpModule, Http } from '@angular/http';
import { Contact } from './../../model/contact';
import { ContactService } from './../../services/contact/contact.service';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
contacts: any;
contact: Contact;
keyWord: string="";
size: number=3;
currentPage: number=0;
pages: Array<number>;
  constructor(private http: Http ,private service: ContactService,private router: Router) { }

  ngOnInit() {
  }

  
  doSearch(){
     this.service.getContacts(this.keyWord,this.size,this.currentPage)
                .subscribe(data => {this.contacts= data;this.pages = new Array(data.totalPages);}, 
                           err => {console.log(err)});
             }  

search(){
    this.doSearch();
        }

getPage(i:number){

  this.currentPage=i;
  this.doSearch(); 
}

onEditContact(id: number)
{
 
   this.router.navigate(['/editContact',id]);
}

onDeleteContact(c: Contact)
{
  let confirm = window.confirm('Are you sure?');
  if(confirm == true){
   this.service.deleteContact(c.id).subscribe( this.contacts.content.splice(this.contacts.content.indexOf(c),1),
                                                      err=>console.log(err));
  }
   this.router.navigate(['/contact']);
}

}
