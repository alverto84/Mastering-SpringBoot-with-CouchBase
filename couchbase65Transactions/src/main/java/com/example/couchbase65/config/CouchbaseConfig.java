package com.example.couchbase65.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import com.couchbase.client.java.Cluster;
import com.couchbase.transactions.Transactions;
import com.couchbase.transactions.config.TransactionConfigBuilder;
@Configuration
@EnableCouchbaseRepositories(basePackages = {"com.example.couchbase65.dao"})
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {
	  @Override
	    public String getConnectionString() {
	        return "couchbase://127.0.0.1";
	    }

	    @Override
	    public String getUserName() {
	        return "admin";
	    }

	    @Override
	    public String getPassword() {
	        return "password";
	    }

	    @Override
	    public String getBucketName() {
	        return "employee";
	    }
	    
		@Bean
		public Transactions transactions(final Cluster couchbaseCluster) {
			return Transactions.create(couchbaseCluster, TransactionConfigBuilder.create()
				// The configuration can be altered here, but in most cases the defaults are fine.
				.build());
		}
}
