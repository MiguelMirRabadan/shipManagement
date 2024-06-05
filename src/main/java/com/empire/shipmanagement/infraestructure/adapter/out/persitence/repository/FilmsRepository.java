package com.empire.shipmanagement.infraestructure.adapter.out.persitence.repository;

import com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilmsRepository extends JpaRepository<FilmEntity, Long> {
    Optional<FilmEntity> findById(Long filmId);
}
