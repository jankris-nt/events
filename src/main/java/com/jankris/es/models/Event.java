package com.jankris.es.models;

import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;
import static org.springframework.data.elasticsearch.annotations.FieldType.Nested;
import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

import java.util.List;
import java.util.Map;

//import org.elasticsearch.common.collect.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName="eventindex")
public class Event {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String status;
    @Field(type = FieldType.Text)
    private Integer seats;
//  @Field(type = FieldType.Object)
//  private List<Person> person;
    @Field(type = FieldType.Nested)
    private List<Person> person;
//    @Field(type = FieldType.Nested, includeInParent = true)
//    private List<Map<String, Object>> person;
}
