package com.aularuix.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aularuix.back.entity.ZonaComun;
import com.aularuix.back.entity.Comedor;
import com.aularuix.back.entity.Hora;

@Repository
public interface ComedorRepository extends JpaRepository<Comedor, Integer>{

}
