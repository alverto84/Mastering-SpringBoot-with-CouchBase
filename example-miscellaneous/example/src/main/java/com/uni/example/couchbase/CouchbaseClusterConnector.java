package com.uni.example.couchbase;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;

@Component
public class CouchbaseClusterConnector {
	private Cluster cluster;
	@Value("${university.bucketRootPassword}")
	private String bucketRootPassword = "password";

	@Value("${university.bucketRootUserName}")
	private String bucketRootUserName = "admin";

	@Value("${university.hosts}")
	private String hosts = "localhost";

	private List<String> getBootstrapHosts() {
		return Arrays.asList(hosts.split(","));
	}

	public CouchbaseClusterConnector() {
		cluster = CouchbaseCluster.create(getBootstrapHosts());
		cluster.authenticate(bucketRootUserName, bucketRootPassword);
	}

	public CouchbaseClusterConnector(List<String> host, String bucketRootUserName, String password) {
		cluster = CouchbaseCluster.create(host);
		cluster.authenticate(bucketRootUserName, password);
	}

	public Cluster getCluster() {
		return cluster;
	}

}
