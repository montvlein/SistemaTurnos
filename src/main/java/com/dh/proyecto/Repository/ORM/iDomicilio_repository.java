package com.dh.proyecto.Repository.ORM;

import com.dh.proyecto.Models.entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iDomicilio_repository extends JpaRepository<Domicilio, Long> {
}
