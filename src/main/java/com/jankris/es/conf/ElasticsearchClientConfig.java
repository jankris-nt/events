/**
 * 
 */
package com.jankris.es.conf;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@Configuration
@EnableElasticsearchRepositories(basePackages = "com.jankris.es.repositories")
@ComponentScan(basePackages = { "com.jankris.es" })
public class ElasticsearchClientConfig extends AbstractElasticsearchConfiguration {

	@Override
	@Bean
	public RestHighLevelClient elasticsearchClient() {

		final ClientConfiguration clientConfiguration = 
				ClientConfiguration
				.builder()
				.connectedTo("elasticsearch:9200")
//				.withBasicAuth(username, password)
				.build();

		return RestClients
				.create(clientConfiguration)
				.rest();
	}
	

}
