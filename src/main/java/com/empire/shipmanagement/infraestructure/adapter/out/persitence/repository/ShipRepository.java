package com.empire.shipmanagement.infraestructure.adapter.out.persitence.repository;

import com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long> {

    Optional<ShipEntity> findById(Long shipId);

    void deleteById(Long shipId);


}
