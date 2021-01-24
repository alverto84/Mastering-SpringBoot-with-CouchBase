package com.uni.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.couchbase.client.java.document.json.JsonArray;
import com.uni.example.dto.StudentCountbyGenderDto;
import com.uni.example.dto.StudentDto;
import com.uni.example.model.Student;
import com.uni.example.model.StudentAttendance;
import com.uni.example.service.StudentService;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping(path = "/save")
	public void saveStudent(@RequestBody Student student) {
		studentService.saveStudent(student);
	}

	@GetMapping(path = "/{id}")
	public Student getStudent(@PathVariable("id") String id) {
		return studentService.findStudent(id);
	}

	@DeleteMapping(path = "/{id}")
	public void deleteStudent(@PathVariable("id") String id) {
		studentService.removetudent(id);
	}

	@PutMapping
	public void updateStudent(@RequestBody Student student) {
		studentService.saveStudent(student);
	}

	@GetMapping(path = "/all")
	public List<Student> getAllStudent() {
		return studentService.findAll();
	}

	@GetMapping(path = "/byName")
	public List<Student> getStudentByName(@RequestParam String fullName) {

		return studentService.findByFullName(fullName);

	}

	@GetMapping(path = "/countByName")
	public Integer getStudentCountByName(@RequestParam String fullName) {

		return studentService.getCountByFullName(fullName);

	}

	@GetMapping(path = "/byNameNickel")
	public List<Student> getStudentByNameNickel(@RequestParam String fullName) {

		return studentService.findByFullNameNickel(fullName);

	}

	@GetMapping(path = "/byNameLikeNickel")
	public List<Student> getStudentByNameLikeNickel(@RequestParam String fullName) {

		return studentService.findByFullNameLikeNickel(fullName);

	}

	@GetMapping(path = "/studentCountNickel")
	public Long getStudentCountNickel() {

		return studentService.getStudentCountNickel();

	}

	@GetMapping(path = "/findAllByIdsNickel")
	public List<Student> findAllByIdsNickel(@RequestParam String[] ids) {

		return studentService.findAllByIdsNickel(JsonArray.from(ids));
	}

	@GetMapping(path = "/byPhoneNickel")
	public List<Student> findAllByPhoneNickel(@RequestParam String phoneNum) {
		return studentService.findAllByPhoneNickel(phoneNum);
	}

	@GetMapping(path = "/byCourseNameNickel")
	public List<Student> findAllbyCourseName(@RequestParam String courseName) {
		return studentService.findAllbyCourseName(courseName);
	}
	@PutMapping(path="/mark")
	public void updateStudentAttendance(@RequestBody StudentAttendance sa) {
		 studentService.saveStudentAttendance(sa);
	}
	@GetMapping(path="/byDateAttendedNickel")
	public List<Student> findStudentsAttendedOnGivenDay(@RequestParam  String day)
	{
		
		return studentService.findStudentsAttendedOnGivenDay(day);
	}
	@GetMapping(path="/byDateAttendedJoinNickel")
	public List<Student> findStudentAttendenceJoin(@RequestParam  String day){
		return studentService.findStudentAttendenceJoin(day);
	}
	
	@DeleteMapping(path="/deleteNickel/{id}")
	public void deleteStudentNickel(@PathVariable("id") String id) {
		studentService.deleteStudent(id);
	}
	@GetMapping(path="/findAllWithTemplate")
	public List<Student> findAllWithCouchBaseTemplate() {
		return studentService.findAllWithCouchBaseTemplate();
		
	}
	@GetMapping(path="/project")
	public List<StudentDto> findAllProjection() {
		return studentService.findAllProjection();
	}



	@GetMapping(path="/projectCountByGender")
	public List<StudentCountbyGenderDto> findStudentCountByGender() {
		return studentService.findStudentCountByGender();
	}


	
}
