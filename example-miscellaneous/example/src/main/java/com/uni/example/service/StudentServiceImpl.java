package com.uni.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uni.example.dto.StudentCountbyGenderDto;
import com.uni.example.dto.StudentDto;
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
	@Autowired
	private CouchbaseTemplate couchbaseTemplate;

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
	public List<Student> findStudentsAttendedOnGivenDay(String day) {
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

	@Override
	public List<Student> findAllWithCouchBaseTemplate() {
		List<Student> list = new ArrayList<>();
		N1qlQueryResult result = couchbaseTemplate.getCouchbaseBucket().query(N1qlQuery.simple(
				"SELECT *, meta().id as id ,meta().cas as version FROM university WHERE _class='com.uni.example.model.Student'"));
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<N1qlQueryRow> rows = result.allRows();
		try {
			for (N1qlQueryRow row : rows) {

				Student s = objectMapper.readValue(row.value().get("university").toString(), Student.class);
				s.setId(row.value().get("id").toString());
				s.setVersion(Long.valueOf(row.value().get("version").toString()));

				list.add(s);
			}
		} catch (Exception e) {
			throw new RuntimeException("Mappping error", e);
		}
		return list;
	}

	@Override
	public List<StudentDto> findAllProjection() {
		return couchbaseTemplate.findByN1QLProjection(
				N1qlQuery.simple("SELECT fullName  FROM university WHERE _class='com.uni.example.model.Student'"),
				StudentDto.class);

	}

	@Override
	public List<StudentCountbyGenderDto> findStudentCountByGender() {
		return couchbaseTemplate.findByN1QLProjection(N1qlQuery.simple(
				"SELECT studentDetails.gender as gender,COUNT(studentDetails.gender) as count FROM university WHERE _class='com.uni.example.model.Student' GROUP BY studentDetails.gender"),
				StudentCountbyGenderDto.class);

	}

}
