package com.uni.example.couchbase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseDataConfiguration;
import org.springframework.data.couchbase.config.CouchbaseConfigurer;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;

import com.uni.example.model.Student;
import com.uni.example.model.StudentAttendance;
import com.uni.example.model.StudentAttendanceRepository;
import com.uni.example.model.StudentNickelRepository;
import com.uni.example.model.StudentRepository;
import com.uni.example.restaurant.model.Order;
import com.uni.example.restaurant.model.OrderRepository;

@Configuration
@EnableCouchbaseRepositories
public class CouchbaseBucketConfig extends AbstractCouchbaseDataConfiguration {
	@Autowired
	private CouchbaseEnvironmentConfiguerer environmentConfigurer;

	@Override
	protected CouchbaseConfigurer couchbaseConfigurer() {
		return environmentConfigurer;
	}

	@Bean
	public CouchbaseTemplate restaurantTemplate() throws Exception {
		CouchbaseTemplate template = new CouchbaseTemplate(couchbaseConfigurer().couchbaseClusterInfo(),
				environmentConfigurer.restaurantBucket(), mappingCouchbaseConverter(), translationService());
		template.setDefaultConsistency(getDefaultConsistency());
		return template;
	}

	@Override
	public void configureRepositoryOperationsMapping(RepositoryOperationsMapping baseMapping) {
		try {
			baseMapping.mapEntity(Student.class, couchbaseTemplate())
					.mapEntity(StudentAttendance.class, couchbaseTemplate())
					.mapEntity(Order.class, restaurantTemplate())

					.map(StudentRepository.class, couchbaseTemplate())
					.map(StudentNickelRepository.class, couchbaseTemplate())
					.map(StudentAttendanceRepository.class, couchbaseTemplate())
					.map(OrderRepository.class, restaurantTemplate());

		} catch (Exception e) {

		}
	}

}
