package com.uni.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.document.json.JsonArray;
import com.uni.example.model.Student;
import com.uni.example.model.StudentAttendance;
import com.uni.example.model.StudentAttendanceRepository;
import com.uni.example.model.StudentNickelRepository;
import com.uni.example.model.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentAttendanceRepository studentAttendanceRepository;

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private StudentNickelRepository studentNickelRepository;
	
	
	@Override
	public void saveStudent(Student student) {

		studentRepository.save(student);
	}

	@Override
	public Student findStudent(String id) {
		return studentRepository.findById(id).orElse(null);

	}

	@Override
	public void removetudent(String id) {
		studentRepository.deleteById(id);

	}

	@Override
	public List<Student> findAll() {

		return (List<Student>) studentRepository.findAll();
	}

	@Override
	public List<Student> findByFullName(String fname) {

		return studentRepository.findByFullName(fname);
	}

	@Override
	public Integer getCountByFullName(String fullName) {
		return studentRepository.getCountByFullName(fullName);

	}

	@Override
	public List<Student> findByFullNameNickel(String fullName) {
		return studentNickelRepository.findByFullNameNickel(fullName);
	}

	@Override
	public List<Student> findByFullNameLikeNickel(String fullName) {

		return studentNickelRepository.findByFullNameLikeNickel(fullName);
	}

	@Override
	public Long getStudentCountNickel() {
		return studentNickelRepository.getStudentCountNickel();
	}

	@Override
	public List<Student> findAllByIdsNickel(JsonArray ids) {

		return studentNickelRepository.findAllByIdsNickel(ids);
	}

	@Override
	public List<Student> findAllByPhoneNickel(String phoneNum) {
		return studentNickelRepository.findAllByPhoneNickel(phoneNum);
	}

	@Override
	public List<Student> findAllbyCourseName(String courseName) {
		// TODO Auto-generated method stub
		return studentNickelRepository.findAllbyCourseName(courseName);
	}
	
	
	@Override
	public void saveStudentAttendance(StudentAttendance sa) {
		
		studentAttendanceRepository.save(sa);
	}

	@Override
	public List<Student> findStudentsAttendedOnGivenDay(String day){
		return studentNickelRepository.findStudentsAttendedOnGivenDay(day);
	}
	@Override
	public List<Student> findStudentAttendenceJoin(String day) {
		
		return studentNickelRepository.findStudentAttendenceJoin(day);
	}
	

	@Override
	public void deleteStudent(String id) {
		
	studentNickelRepository.deleteStudent(id);
		
	}
}
