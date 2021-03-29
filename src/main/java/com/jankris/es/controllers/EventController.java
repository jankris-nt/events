package com.jankris.es.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jankris.es.models.Event;
import com.jankris.es.models.Person;
import com.jankris.es.repositories.EventRepository;
import com.jankris.es.repositories.PersonRepository;
// import lombok.extern.slf4j.Slf4j;

@Component
@CrossOrigin("*")
@RestController
@RequestMapping("/")
// @Slf4j
public class EventController {
	private  EventRepository eventRepo;
	private  PersonRepository perRepo;
	
	@Autowired
	public EventController(EventRepository eventRepo, PersonRepository perRepo) { 
	    this.eventRepo = eventRepo;
	    this.perRepo = perRepo;
	}

	@RequestMapping(value = "/event", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Object> createEvent(@RequestBody Event event) {
		Iterable<Event> tmpEventList = eventRepo.findAll();
		for (Event e : tmpEventList) {
		    if (e.getName().equals(event.getName())) {
		    	return new ResponseEntity<>("Event name already exist", HttpStatus.OK);
		    }
		}
		eventRepo.save(event);
		return new ResponseEntity<>("Event is created successfully", HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/event/{id}/status", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<Object> statusEvent(@PathVariable("id") String id) {
		Optional<Event> tmpEvent = eventRepo.findById(id);
		if (! tmpEvent.isPresent()) {
			return new ResponseEntity<>("Event not found", HttpStatus.OK);
		}
		return new ResponseEntity<>(eventRepo.findById(id).get().getStatus(), HttpStatus.OK);
	}

	@RequestMapping(value = "/event/{id}/free", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<Object> freeSeats(@PathVariable("id") String id) {
		Optional<Event> tmpEvent = eventRepo.findById(id);
		if (! tmpEvent.isPresent()) {
			return new ResponseEntity<>("Event not found", HttpStatus.OK);
		}
		tmpEvent.get().getSeats();		
		Optional<Object> tmpPersons = tmpEvent.map(event -> {
			return event.getPerson();
		});	
		if (! tmpPersons.isPresent()) {
			return new ResponseEntity<>(tmpEvent.get().getSeats() , HttpStatus.OK);
		}
		@SuppressWarnings("unchecked")
		List<Person> lstPersons = (List<Person>) tmpPersons.get();
		Integer freePlaces = eventRepo.findById(id).get().getSeats() - lstPersons.size();
		return new ResponseEntity<>(freePlaces , HttpStatus.OK);			
	}
	
	@RequestMapping(value = "/event/{id}/status/{newStatus}", method = RequestMethod.PUT, produces="application/json")
	public ResponseEntity<Object> updateStatusEvent(
			@PathVariable("id") String id,
			@PathVariable("newStatus") String newStatus) {
		Optional<Event> existEvent = eventRepo.findById(id);
		if (! existEvent.isPresent()) {
			return new ResponseEntity<>("Event not found", HttpStatus.OK);
		}
		existEvent.map(event -> {
	        event.setStatus(newStatus);
	        return eventRepo.save(event);
		});
		return new ResponseEntity<>("Event status updated successfully", HttpStatus.OK);
	}		

	@RequestMapping(value = "/event", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<Object> findAllEvent() {
		return ResponseEntity.ok(eventRepo.findAll());
	}
	
	@RequestMapping(value = "/event/{id}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<Object> findEvent(@PathVariable("id") String id) {
		return ResponseEntity.ok(eventRepo.findById(id));
	}
	
	@RequestMapping(value = "/eventdelete/{id}", method = RequestMethod.DELETE, produces="application/json")
	public ResponseEntity<Object> deleteEvent(@PathVariable("id") String id) {
		Optional<Event> tmpEvent = eventRepo.findById(id);
		if (! tmpEvent.isPresent()) {
			return new ResponseEntity<>("Event not found", HttpStatus.OK);
		}
		eventRepo.deleteById(id);
		return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/event/{id}/person", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Object> addPersonToEvent(
			@PathVariable("id") String id, 
			@RequestBody Person newPerson) {
		
		Optional<Event> existEvent = eventRepo.findById(id);
		if  (!existEvent.isPresent()) {
			return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
		}
		Optional<Object> existPersons = existEvent.map(event -> {
			return event.getPerson();
		});
		if (existPersons.isPresent()) {
			if (existEvent.get().getSeats() > 0) {
				@SuppressWarnings("unchecked")
				List<Person> lstPersons = (List<Person>) existPersons.get();
				if (lstPersons.size() < existEvent.get().getSeats()) {
					lstPersons.add(newPerson);
					existEvent.map(event -> {
				        event.setPerson(lstPersons);
				        return eventRepo.save(event);
					});
					return new ResponseEntity<>("Person added successfully", HttpStatus.OK);
				} else {
					return new ResponseEntity<>("FULL!", HttpStatus.NOT_ACCEPTABLE);
				}
			} else {
				return new ResponseEntity<>("No seats", HttpStatus.OK);
			}					
		} else {
			List<Person> renewPersonList = new ArrayList<>();
			renewPersonList.add(newPerson);
			existEvent.map(event -> {
		        event.setPerson(renewPersonList);
		        return eventRepo.save(event);
			});
			return new ResponseEntity<>("Person added successfully", HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/event/{id}/person/{name}", method = RequestMethod.DELETE, produces="application/json")
	public ResponseEntity<Object> deletePersonFromEvent(
			@PathVariable("id") String id, 
			@PathVariable("name") String perName) {
		
		Optional<Event> existEvent = eventRepo.findById(id);
		if  (!existEvent.isPresent()) {
			return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
		}
		Optional<Object> existPersons = existEvent.map(event -> {
			// List<Person> person = event.getPerson();
			return event.getPerson();
		});
		if  (!existPersons.isPresent()) {
			return new ResponseEntity<>("Persons not found", HttpStatus.NOT_FOUND);
		}

		@SuppressWarnings("unchecked")
		List<Person> lstPersons = (List<Person>) existPersons.get();

		List<Person> renewPersonList = new ArrayList<>();
		Boolean existPerson = false;
        for (int i = 0; i < lstPersons.size(); i++) {
        	Person per = lstPersons.get(i);
            if (per.getName().equals(perName)) {
            	existPerson = true;
            } else {
            	renewPersonList.add(per);
            };
        }	;
		eventRepo.findById(id).map(event -> {
	        event.setPerson(renewPersonList);
	        return eventRepo.save(event);
		});		

		if (existPerson) {
			return new ResponseEntity<>("Person removed successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Person not exist", HttpStatus.OK);
		}
		
	}	
	
}
