package com.uni.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Version;
import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

@Document
public class Student {
	@Id
	private String id;
	@Field
	private String fullName;
	@Field
	private StudentDetails studentDetails;
	@Field
	private List<Course> courses = new ArrayList<>();

	@Field
	private Map<String, String> paymentInfo = new HashMap<>();
	@Version
   private long version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public StudentDetails getStudentDetails() {
		return studentDetails;
	}

	public void setStudentDetails(StudentDetails studentDetails) {
		this.studentDetails = studentDetails;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Map<String, String> getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(Map<String, String> paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	

}
