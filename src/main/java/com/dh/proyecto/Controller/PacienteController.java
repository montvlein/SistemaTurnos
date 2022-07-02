package com.dh.proyecto.Controller;

import com.dh.proyecto.Models.entities.Paciente;
import com.dh.proyecto.Services.iServices;
import com.dh.proyecto.Services.implement.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/paciente")
public class PacienteController {

    private iServices<Paciente> services;

    @Autowired
    @Qualifier("paciente_services")
    public void setServices(iServices services) {
        this.services = services;
    }

    @PostMapping
    public ResponseEntity registar(@RequestBody Paciente p) {
        return ResponseEntity.ok(services.guardar(p));
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

    @PutMapping
    public ResponseEntity<Paciente> actualizarRegistro(@RequestBody Paciente paciente){
        ResponseEntity<Paciente> response = ResponseEntity.notFound().build();
        if(paciente.getId() != null && services.buscar(paciente.getId()) != null){
            // Este lo busca en el body (Si le estan mandado por postman) && este lo busca en el com.odontologica.proyectfinal.repository
            response = ResponseEntity.ok(((PacienteService)services).actualizar(paciente));
        }
        return response;
    }

    @GetMapping("all")
    public ResponseEntity listarTodos() {
        return ResponseEntity.ok(services.listarTodos());
    }

}
