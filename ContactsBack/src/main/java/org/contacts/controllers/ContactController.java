package org.contacts.controllers;

import java.util.List;

import org.contacts.dao.ContactRepository;
import org.contacts.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
	/**
	 * @author Elimane Fofana
	 *
	 * 
	 */
	@Autowired
	private ContactRepository repo;
	
	@GetMapping(value="/contacts")
	public List<Contact> getAllContacts()
	{
		return repo.findAll();
	}
	
	@GetMapping(value="/contact/{id}")
	public Contact getContact(@PathVariable Long id)
	{
		return repo.findOne(id);
	}
	
	@PostMapping(value="/contact")
	public Contact getContact(@RequestBody Contact c)
	{
		return repo.save(c);
	}
	
	@DeleteMapping(value="/deleteContact/{id}")
	public boolean deleteContact(@PathVariable Long id)
	{
		repo.delete(id);
		return true;
	}
	
	@PutMapping(value="/updateContact/{id}")
	public Contact getContact(@RequestBody Contact c, @PathVariable Long id)
	{
		c.setId(id);
		return repo.save(c);
	}
	
	@GetMapping(value="/searchContact")
	public Page<Contact> getContact(@RequestParam(name="mc",defaultValue="")String mc,@RequestParam(name="size",defaultValue="3") int size,@RequestParam(name="page",defaultValue="0") int page)
	{
		return repo.search("%"+mc+"%", new PageRequest(0, 3));
	}
	
	
}
