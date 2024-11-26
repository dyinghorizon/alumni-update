package com.nishad.alumniupdate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "students")
@Data
public class Student {
    @Id
    @Column(name = "student_id")
    private String studentId;  // Format: 2020MT01

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "photograph_path")
    private String photographPath;

    private Double cgpa;

    @Column(name = "total_credits")
    private Integer totalCredits;

    @Column(name = "graduation_year")
    private Integer graduationYear;

    // We don't need @OneToOne with Alumni since this is a read-only entity
}