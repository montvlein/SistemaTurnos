package com.dh.proyecto.Models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class TurnoDTO {

    private String fecha;
    private String hora;
    private String paciente;
    private String odontologo;
}
