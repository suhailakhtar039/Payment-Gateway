package com.payment.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rollNo;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "rollNo"),
            inverseJoinColumns = @JoinColumn(name="course_id", referencedColumnName = "courseId")
    )
    @ToString.Exclude
    @JsonIgnore
    private Set<Course> coursesEnrolledIn = new HashSet<>();

}
