package com.dh.proyecto.Services.implement;

import com.dh.proyecto.Models.entities.Odontologo;
import com.dh.proyecto.Services.iServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class OdontologoServiceTest {

    @Autowired
    @Qualifier("odontologo_services")
    private iServices<Odontologo> services;

    private Odontologo odontologo;

    @BeforeEach
    public void cargarDataSet() {
        odontologo = new Odontologo();
    }

    @Test
    public void buscar() {
    }

    @Test
    public void guardarOdontologoEnDB() throws Exception {
        services.guardar(odontologo);
        Odontologo o = services.buscar(1L);
        Assertions.assertNotNull(o);
    }

    @Test
    public void eliminar() {
    }

    @Test
    public void listarTodos() {
    }
}