/**
 * 
 */
package com.jankris.es.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName="personindex")
public class Person {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String name;
//    @Field(type = FieldType.Text)
//    private String summary;
//    @Field(type = FieldType.Integer)
//    private Integer price;
	// getter/setter ...
}