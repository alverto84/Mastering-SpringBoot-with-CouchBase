package com.uni.example.restaurant.model;

import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

public interface OrderRepository extends CouchbasePagingAndSortingRepository<Order, String> {

}
