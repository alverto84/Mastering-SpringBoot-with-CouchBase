package com.uni.example.dto;

public class StudentCountbyGenderDto {
	private String gender;
	private Integer count;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public StudentCountbyGenderDto(String gender, Integer count) {
		this.gender = gender;
		this.count = count;
	}

	public StudentCountbyGenderDto() {

	}
}
