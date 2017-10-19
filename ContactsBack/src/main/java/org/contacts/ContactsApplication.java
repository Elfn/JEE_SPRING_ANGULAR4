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
		repo.save(new Contact("Eric", "Doe", df.parse("04/11/1988"), "e@gmail.com", 0607060706, "me/directory/photo"));
		repo.save(new Contact("John", "Moe", df.parse("02/03/1987"), "m@gmail.com", 0607060706, "me/directory/photo"));
		repo.save(new Contact("Ghafi", "Ma", df.parse("02/03/1993"), "g@gmail.com", 0607060706, "me/directory/photo"));
		repo.save(new Contact("Karim", "Salim", df.parse("06/06/1991"), "k@gmail.com", 0607060706, "me/directory/photo"));
		repo.save(new Contact("Karim", "Assouli", df.parse("08/04/1991"), "a@gmail.com", 0607060706, "me/directory/photo"));
		repo.save(new Contact("Eee", "Erdff", df.parse("06/06/1991"), "r@gmail.com", 0607060706, "me/directory/photo"));
		repo.save(new Contact("Kobd", "asdc", df.parse("02/10/1988"), "kobd@gmail.com", 0607060706, "me/directory/photo"));
		repo.save(new Contact("zoe", "kerkwwe", df.parse("02/02/1980"), "z@gmail.com", 0607060706, "me/directory/photo"));

		repo.findAll().forEach(c -> System.out.println(c.getFname()));
	}
}
