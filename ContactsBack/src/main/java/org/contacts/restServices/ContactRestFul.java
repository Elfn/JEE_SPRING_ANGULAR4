package org.contacts.restServices;

import java.util.List;

import org.contacts.dao.ContactRepository;
import org.contacts.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
//@CrossOrigin("*")//Second method to enable access control
public class ContactRestFul {
	/**
	 * @author Elimane Fofana
	 *
	 * 
	 */
	@Autowired
	private ContactRepository repo;
	
	@RequestMapping("/")
    public String home()
    {
	 return "<h1>Back end server is running ...</h1>";
    }
	
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
	
	@PostMapping(value="/addContact")
	public Contact addContact(@RequestBody Contact c)
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
	public Contact updateContact(@RequestBody Contact c, @PathVariable Long id)
	{
		c.setId(id);
		return repo.save(c);
	}
	
	@GetMapping(value="/searchContact")
	public Page<Contact> getContact(@RequestParam(name="mc",defaultValue="")String mc,@RequestParam(name="size",defaultValue="5") int size,@RequestParam(name="page",defaultValue="0") int page)
	{
		return repo.search("%"+mc+"%", new PageRequest(page,size));
	}
	
	
}
