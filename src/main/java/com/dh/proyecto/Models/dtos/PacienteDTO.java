package com.dh.proyecto.Models.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PacienteDTO {
    private Long id;
    private String apellido;
    private String nombre;
    private String dni;
    private String domicilio;
}
