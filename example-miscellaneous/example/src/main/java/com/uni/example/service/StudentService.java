package com.uni.example.service;

import java.util.List;

import com.couchbase.client.java.document.json.JsonArray;
import com.uni.example.dto.StudentCountbyGenderDto;
import com.uni.example.dto.StudentDto;
import com.uni.example.model.Student;
import com.uni.example.model.StudentAttendance;

public interface StudentService {
	public void saveStudent(Student student);

	public Student findStudent(String id);

	public void removetudent(String id);

	public List<Student> findAll();

	public List<Student> findByFullName(String fullName);

	public Integer getCountByFullName(String fullName);

	public List<Student> findByFullNameNickel(String fullName);

	public List<Student> findByFullNameLikeNickel(String fullName);

	public Long getStudentCountNickel();

	public List<Student> findAllByIdsNickel(JsonArray ids);

	public List<Student> findAllByPhoneNickel(String phoneNum);

	public List<Student> findAllbyCourseName(String courseName);

	public void saveStudentAttendance(StudentAttendance sa);

	List<Student> findStudentsAttendedOnGivenDay(String day);

	List<Student> findStudentAttendenceJoin(String day);

	public void deleteStudent(String id);
	public List<Student> findAllWithCouchBaseTemplate();
	public List<StudentDto> findAllProjection();
	public List<StudentCountbyGenderDto> findStudentCountByGender();


}
