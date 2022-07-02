package com.dh.proyecto.Controller;

import com.dh.proyecto.Models.entities.Turno;
import com.dh.proyecto.Services.iServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/turno")
public class TurnoController {

    private iServices<Turno> services;

    @Autowired
    @Qualifier("turno_services")
    public void setServices(iServices services) {
        this.services = services;
    }

    @PostMapping
    public ResponseEntity registar(@RequestBody Turno t) {
        return ResponseEntity.ok(services.guardar(t));
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
