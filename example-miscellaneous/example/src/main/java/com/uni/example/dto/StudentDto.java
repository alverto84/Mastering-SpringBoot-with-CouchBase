package com.uni.example.dto;

public class StudentDto {
private String fullName;

public StudentDto(String fullName) {
	
	this.fullName = fullName;
}
public StudentDto() {
	
}
public String getFullName() {
	return fullName;
}
public void setFullName(String fullName) {
	this.fullName = fullName;
}


}
