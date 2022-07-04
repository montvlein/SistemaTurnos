package com.dh.proyecto.Services.implement;

import com.dh.proyecto.Models.entities.Paciente;
import com.dh.proyecto.Repository.ORM.iPaciente_repository;
import com.dh.proyecto.Services.iServices;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("paciente_services")
public class PacienteService implements iServices<Paciente> {

    public static Logger logger = Logger.getLogger(PacienteService.class);
    private iPaciente_repository paciente_repository;

    @Autowired
    public void setPaciente_repository(iPaciente_repository paciente_repository) {
        this.paciente_repository = paciente_repository;
    }

    public boolean guardar(Paciente paz) {
        boolean res = false;
        try {
            paciente_repository.save(paz);
            res = true;
        } catch (Exception e) {
            logger.info(e);
        }
        return res;
    }

    public boolean eliminar(Long id) {
        boolean res = false;
        try {
            paciente_repository.deleteById(id);
            res = true;
        } catch (Exception e) {
            logger.info(e);
        }
        return res;
    }

    public Paciente buscar(Long id) {
        return paciente_repository.findById(id).get();
    }

    public Paciente actualizar(Paciente paciente){
        Paciente pacienteActualizar = paciente_repository.findById(paciente.getId()).get();
        pacienteActualizar.setNombre(paciente.getNombre());
        pacienteActualizar.setApellido(paciente.getApellido());
        pacienteActualizar.setDni(paciente.getDni());
        pacienteActualizar.setFechaIngreso(paciente.getFechaIngreso());
        guardar(pacienteActualizar);
        return paciente;
    }

    public List<Paciente> listarTodos() {
        return paciente_repository.findAll();
    }
}
