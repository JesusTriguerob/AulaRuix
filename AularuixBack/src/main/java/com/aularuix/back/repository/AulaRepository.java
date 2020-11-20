package com.aularuix.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aularuix.back.entity.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer>{

}
