package com.dh.proyecto.Controller;

import com.dh.proyecto.Exceptions.BadRequestException;
import com.dh.proyecto.Models.dtos.TurnoDTO;
import com.dh.proyecto.Models.entities.Odontologo;
import com.dh.proyecto.Models.entities.Paciente;
import com.dh.proyecto.Models.entities.Turno;
import com.dh.proyecto.Services.iServices;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/turno")
public class TurnoController {

    public Logger logger = Logger.getLogger(TurnoController.class);
    private iServices<Turno> services;

    @Autowired
    @Qualifier("turno_services")
    public void setServices(iServices<Turno> services) {
        this.services = services;
    }

    @PostMapping
    public ResponseEntity registar(@RequestBody Turno t) {
        ResponseEntity respuesta = null;
        Paciente paciente_a_atender = t.getPaciente();
        Odontologo dentista = t.getOdontologo();
        if (paciente_a_atender.getId() == null
                || dentista.getId() == null
                || t.getFecha_y_hora() == null
        ) respuesta = ResponseEntity.badRequest().body("no se selecciono paciente, odontologo o turno");
        try {
            boolean respuesta_despues_guardar = services.guardar(t);
            respuesta = ResponseEntity.ok(respuesta_despues_guardar);
        } catch (BadRequestException e){
            logger.info(e);
            respuesta = ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.info(e);
            respuesta = ResponseEntity.badRequest().body(e.getMessage());
        }
        return respuesta;
    }

    @GetMapping("{id}")
    public ResponseEntity buscar(@PathVariable Long id) {
        return ResponseEntity.ok(services.buscar(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        services.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity listarTodos() {
        List<Turno> listaTurnos = services.listarTodos();
        List<TurnoDTO> listaRespuesta = new ArrayList<>();
        for (Turno t:listaTurnos) {
            TurnoDTO tDTO = new TurnoDTO();
            tDTO.setFecha(t.getFecha_y_hora().getDayOfMonth() + "/" + t.getFecha_y_hora().getMonthValue() + "/" + t.getFecha_y_hora().getYear());
            tDTO.setHora(t.getFecha_y_hora().getHour() + ":" + t.getFecha_y_hora().getMinute());
            tDTO.setOdontologo(t.getOdontologo().getApellido() + ", " + t.getOdontologo().getNombre());
            tDTO.setPaciente(t.getPaciente().getApellido() + ", " + t.getPaciente().getNombre());
            listaRespuesta.add(tDTO);
        }
        return ResponseEntity.ok(listaRespuesta);
    }

}
