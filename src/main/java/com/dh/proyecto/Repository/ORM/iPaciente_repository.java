package com.dh.proyecto.Repository.ORM;

import com.dh.proyecto.Models.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface iPaciente_repository extends JpaRepository<Paciente, Long> {

    @Query("select p from Paciente p where p.nombre = ?1")
    List<Paciente> findPacienteByName(String name);

}
