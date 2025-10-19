package com.payment.gateway.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    private String courseName;

    private int courseRating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference   // prevents serializing course.teacher (breaks the cycle)
    private Teacher teacher;

    @ManyToMany(mappedBy = "coursesEnrolledIn", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Student> studentsEnrolled = new HashSet<>();

}
