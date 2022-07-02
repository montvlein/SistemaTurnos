package com.dh.proyecto.Repository.ORM;

import com.dh.proyecto.Models.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iTurno_repository extends JpaRepository<Turno, Long> {
}
