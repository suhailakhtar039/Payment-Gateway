package com.payment.gateway.controller;

import com.payment.gateway.dto.CreateCourseRequest;
import com.payment.gateway.dto.EnrollRequest;
import com.payment.gateway.entity.Course;
import com.payment.gateway.entity.Student;
import com.payment.gateway.entity.Teacher;
import com.payment.gateway.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EducationController {
    private final EducationService svc;
    @PostMapping("/teachers")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher t) {
        return ResponseEntity.ok(svc.createTeacher(t));
    }

    @PostMapping("/teachers/{teacherId}/courses")
    public ResponseEntity<Course> createCourse(@PathVariable Integer teacherId,
                                               @RequestBody CreateCourseRequest req) {
        Course c = new Course();
        c.setCourseName(req.getCourseName());
        c.setCourseRating(req.getCourseRating());
        return ResponseEntity.ok(svc.createCourse(teacherId, c));
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student s) {
        return ResponseEntity.ok(svc.createStudent(s));
    }

    @PostMapping("/enroll")
    public ResponseEntity<String> enroll(@RequestBody EnrollRequest req) {
        svc.enrollStudentInCourse(req.getStudentId(), req.getCourseId());
        return ResponseEntity.ok("enrolled");
    }

    @GetMapping("/teachers/{teacherId}/students")
    public ResponseEntity<List<Student>> studentsOfTeacher(@PathVariable Integer teacherId) {
        return ResponseEntity.ok(svc.getStudentByTeacher(teacherId));
    }
}
