package com.dh.proyecto.Services;

//
//import com.dh.proyecto.Models.entities.Odontologo;
//import com.dh.proyecto.Services.implement.OdontologoService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.Optional;
//
//@RunWith(JUnit4.class)
//@SpringBootTest
//public class odontologoServiceTest {
//
//    private iServices<Odontologo> servicio = new OdontologoService();
//
//    @Test
//    public void _01_guardarOdontologo() {
//        Odontologo odontologo = new Odontologo();
//        boolean isSaved = servicio.guardar(odontologo);
//        Assert.assertTrue(isSaved);
//    }
//
//    @Test
//    public void _02_listarOdontologos() {
//        servicio.guardar(new Odontologo());
//        List<Odontologo> listaOdontologos = servicio.listarTodos();
//        Assert.assertTrue("Se devuelve una lista por lo menos con más de un dato", listaOdontologos.size() > 0);
//    }
//
//    @Test
//    public void _03_buscarOdontologoPorId() {
//        Odontologo mostWanted = servicio.buscar(999_999L);
//        Assert.assertEquals(Optional.of(999_999L), mostWanted.getId());
//    }
//
//    @Test
//    public void _04_eliminarOdontologoPorId() {
//        boolean eliminado = servicio.eliminar(999_999L);
//        Assert.assertTrue(eliminado);
//    }
//}
