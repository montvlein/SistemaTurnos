package com.dh.proyecto.Repository.ORM;

import com.dh.proyecto.Models.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iOdontologo_repository extends JpaRepository<Odontologo, Long> {
}
