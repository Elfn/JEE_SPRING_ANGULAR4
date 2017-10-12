package org.contacts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.contacts.dao.ContactRepository;
import org.contacts.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactsApplication implements CommandLineRunner{

	@Autowired
	ContactRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(ContactsApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		repo.save(new Contact("Fofana", "Elimane", df.parse("12/01/1991"), "e@gmail.com", 0607060706, "me/directory/photo"));
		
		repo.findAll().forEach(c -> System.out.println(c.getFname()));
	}
}
