package com.uni.example.model;

import java.util.List;

import org.springframework.data.couchbase.core.query.View;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

@ViewIndexed(designDoc="student")
public interface StudentRepository extends CouchbasePagingAndSortingRepository<Student, String> {
	@View(viewName="byFullName")
	public List<Student> findByFullName(String fullName);
	@View(viewName="countByFullName",reduce=true)
	public Integer getCountByFullName(String fullName);
}
