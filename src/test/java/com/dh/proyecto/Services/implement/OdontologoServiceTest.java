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
    public void cargarDataSet() throws Exception {
        odontologo = new Odontologo();
        odontologo.setMatricula("1234");
        services.guardar(odontologo);
    }

    @Test
    public void buscar() {
        Odontologo o = services.buscar(1L);
        Assertions.assertEquals("1234", o.getMatricula());
    }

    @Test
    public void guardarOdontologoEnDB() throws Exception {
        services.guardar(new Odontologo());
        Odontologo o = services.buscar(2L);
        Assertions.assertNotNull(o);
    }

    @Test
    public void eliminar() {
        services.eliminar(1L);
        Odontologo o = services.buscar(1L);
        Assertions.assertNull(o);
    }

    @Test
    public void listarTodos() {
        Assertions.assertNotEquals(0, services.listarTodos().size());
    }
}