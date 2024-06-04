package com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Films")
public class FilmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private FilmType type;
    @Column(nullable = false)
    private LocalDate releasedDate;
    @Column(nullable = false)
    private String description;
}
