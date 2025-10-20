package com.payment.gateway.service.impl;

import com.payment.gateway.entity.Course;
import com.payment.gateway.entity.Student;
import com.payment.gateway.entity.Teacher;
import com.payment.gateway.repository.CourseRepository;
import com.payment.gateway.repository.StudentRepository;
import com.payment.gateway.repository.TeacherRepository;
import com.payment.gateway.service.EducationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {
    private final StudentRepository studentRepo;
    private final TeacherRepository teacherRepo;
    private final CourseRepository courseRepo;

    @Transactional
    @Override
    public Teacher createTeacher(Teacher t) {
        return teacherRepo.save(t);
    }

    @Transactional
    @Override
    public Course createCourse(Integer teacherId, Course course) {
        Teacher t = teacherRepo.findById(teacherId).
                orElseThrow(() -> new EntityNotFoundException("Teacher not found"));
        course.setTeacher(t);
        Course saved = courseRepo.save(course);
        t.getCoursesMade().add(saved);
        return saved;
    }

    @Transactional
    @Override
    public Student createStudent(Student s) {
        return studentRepo.save(s);
    }

    @Transactional
    @Override
    public void enrollStudentInCourse(int studentId, int courseId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Student not found")
                );
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Course not found")
                );

        student.getCoursesEnrolledIn().add(course);
        course.getStudentsEnrolled().add(student);

        studentRepo.save(student);
    }

    @Transactional
    @Override
    public List<Student> getStudentByTeacher(int teacherId) {
        List<Course> courses = courseRepo.findByTeacherTeacherId(teacherId);
        return courses.stream()
                .flatMap(c -> c.getStudentsEnrolled().stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
