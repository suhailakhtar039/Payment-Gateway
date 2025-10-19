package com.payment.gateway.repository;

import com.payment.gateway.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByTeacherTeacherId(Integer teacherId);
}
