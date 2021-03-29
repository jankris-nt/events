package com.jankris.es.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.jankris.es.models.Event;
import com.jankris.es.models.Person;


@Repository
public interface EventRepository extends ElasticsearchRepository<Event, String> {

    List<Event> findByName(String name);
    
    @Query("{\"bool\": {\"must\": [{\"match\": {\"person.name\": \"?0\"}}]}}")
    List<Event> findByAuthorsNameUsingCustomQuery(String name);
}
