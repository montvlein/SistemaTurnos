package com.dh.proyecto.Controller;

import com.dh.proyecto.Exceptions.NotFoundException;
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

import java.time.LocalDateTime;
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
    public ResponseEntity<?> registar(@RequestBody Turno t) throws Exception {
        ResponseEntity<?> respuesta;
        Paciente paciente_a_atender = t.getPaciente();
        Odontologo dentista = t.getOdontologo();
        if (paciente_a_atender.getId() == null) { respuesta = ResponseEntity.badRequest().body("no se selecciono paciente"); }
        else if (dentista.getId() == null) { respuesta = ResponseEntity.badRequest().body("no se selecciono paciente u odontologo"); }
        else if (t.getFecha_y_hora().isBefore(LocalDateTime.now())) { respuesta = ResponseEntity.badRequest().body("el turno debe ser posterior a la fecha y hora actual"); }
        else { respuesta = ResponseEntity.ok(services.guardar(t)); }
        return respuesta;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) throws Exception {
        Turno t = services.buscar(id);
        if (t == null) throw new NotFoundException("no existe turno con ese ID");
        return ResponseEntity.ok(t);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) throws Exception {
        if (services.buscar(id) == null) throw new NotFoundException("no existe turno con ese ID");
        services.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<?> listarTodos() {
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
