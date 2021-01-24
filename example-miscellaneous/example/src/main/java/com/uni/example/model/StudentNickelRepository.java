package com.uni.example.model;

import java.util.List;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.couchbase.client.java.document.json.JsonArray;

@N1qlPrimaryIndexed
public interface StudentNickelRepository extends CouchbasePagingAndSortingRepository<Student, String> {
	@Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND  fullName = $1 ORDER BY fullName DESC")
	public List<Student> findByFullNameNickel(String fullName);

	@Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND fullName LIKE '%#{[0]}%'")
	List<Student> findByFullNameLikeNickel(String fullname);

	@Query("SELECT count(*) as count FROM #{#n1ql.bucket} WHERE #{#n1ql.filter} ")
	Long getStudentCountNickel();

	@Query("#{#n1ql.selectEntity} WHERE meta().id IN $ids AND #{#n1ql.filter}")
	List<Student> findAllByIdsNickel(@Param("ids") JsonArray ids);

	@Query("#{#n1ql.selectEntity} WHERE studentDetails.phoneNum = $1 AND #{#n1ql.filter}")
	List<Student> findAllByPhoneNickel(String phoneNum);

	@Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND (SOME course IN courses SATISFIES course.courseName = $1  END)")
	List<Student> findAllbyCourseName(String courseName);
	
	@Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND META().id IN (SELECT  RAW studentId FROM university un where _class='com.uni.example.model.StudentAttendance' AND date = $1 AND attended = true) ")
	 List<Student> findStudentsAttendedOnGivenDay(String day);
	
	 @Query("SELECT  a.*, META(a).id AS _ID, META(a).cas AS _CAS   FROM university r JOIN university a ON r.studentId=META(a).id WHERE r.date=$1 AND r.attended = true")
	 List<Student> findStudentAttendenceJoin(String day);
	 @Query("#{#n1ql.delete} WHERE meta().id = $1") 
	 List<Student>	 deleteStudent(String id) ;
}
