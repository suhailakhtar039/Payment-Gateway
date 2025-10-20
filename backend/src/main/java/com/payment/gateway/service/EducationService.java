package com.payment.gateway.service;

import com.payment.gateway.entity.Course;
import com.payment.gateway.entity.Student;
import com.payment.gateway.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EducationService {
    Teacher createTeacher(Teacher t);
    Course createCourse(Integer teacherId, Course course);
    Student createStudent(Student s);
    void enrollStudentInCourse(int studentId, int courseId);
    List<Student> getStudentByTeacher(int teacherId);
}
