package com.jankris.es.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.jankris.es.models.Person;


@Repository
public interface PersonRepository extends ElasticsearchRepository<Person, String> {

    List<Person> findByName(String name);
}