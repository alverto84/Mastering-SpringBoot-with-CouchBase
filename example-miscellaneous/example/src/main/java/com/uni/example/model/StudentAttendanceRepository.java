package com.uni.example.model;

import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

public interface StudentAttendanceRepository extends CouchbasePagingAndSortingRepository<StudentAttendance, String> {

}
