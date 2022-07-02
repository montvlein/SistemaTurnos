package com.dh.proyecto.Repository.ORM;

import com.dh.proyecto.Models.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iPaciente_repository extends JpaRepository<Paciente, Long> {
}
