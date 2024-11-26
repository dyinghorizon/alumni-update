package com.nishad.alumniupdate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "alumni_education")
@Data
public class AlumniEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumni_id", nullable = false)
    private Alumni alumni;

    @Column(nullable = false)
    private String degree;

    @Column(name = "college_name", nullable = false)
    private String collegeName;

    @Column(length = 500)
    private String address;

    @Column(name = "joining_year", nullable = false)
    private Integer joiningYear;

    @Column(name = "passing_year", nullable = false)
    private Integer passingYear;
}