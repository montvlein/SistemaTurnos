package com.dh.proyecto.Controller;

import com.dh.proyecto.Exceptions.BadRequestException;
import com.dh.proyecto.Models.entities.Odontologo;
import com.dh.proyecto.Models.entities.Paciente;
import com.dh.proyecto.Models.entities.Turno;
import com.dh.proyecto.Services.iServices;
import com.dh.proyecto.Services.implement.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/turno")
public class TurnoController {

    public Logger logger = Logger.getLogger(TurnoController.class);
    private iServices<Turno> services;

    @Autowired
    @Qualifier("turno_services")
    public void setServices(iServices services) {
        this.services = services;
    }

    @PostMapping
    public ResponseEntity registar(@RequestBody Turno t) throws BadRequestException {
        ResponseEntity respuesta = null;
        Paciente paciente_a_atender = t.getPaciente();
        Odontologo dentista = t.getOdontologo();
        if (paciente_a_atender.getId() == null
                || dentista.getId() == null) respuesta = ResponseEntity.badRequest().body("no se selecciono paciente u odontologo");
//        if (services.guardar(t)) {
//            respuesta = ResponseEntity.ok().build();
//        }
        try {
            boolean respuesta_despues_guardar = ((TurnoService)services).guardarTurno(t);
            respuesta = ResponseEntity.ok(respuesta_despues_guardar);
        } catch (BadRequestException e){
            logger.info(e);
            respuesta = ResponseEntity.badRequest().body("bad request desde el metodo");
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
        return ResponseEntity.ok(services.listarTodos());
    }

}
