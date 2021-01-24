package com.uni.example.model;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

@Document
public class StudentAttendance {
	@Id
	private String id;
	@Field
	private String studentId;
	@Field
	private String date;
	@Field
	private Boolean attended;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Boolean getAttended() {
		return attended;
	}
	public void setAttended(Boolean attended) {
		this.attended = attended;
	}

}
