package com.example.visitsservice.infrastructure.repositories.mysql;

import com.example.visitsservice.infrastructure.entities.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<VisitEntity, Long> {

}
