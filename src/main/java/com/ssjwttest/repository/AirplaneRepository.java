package com.ssjwttest.repository;

import com.ssjwttest.entities.Airplane;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
}
