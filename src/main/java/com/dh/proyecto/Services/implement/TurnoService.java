package com.dh.proyecto.Services.implement;

import com.dh.proyecto.Exceptions.BadRequestException;
import com.dh.proyecto.Exceptions.NotFoundException;
import com.dh.proyecto.Models.entities.Odontologo;
import com.dh.proyecto.Models.entities.Paciente;
import com.dh.proyecto.Models.entities.Turno;
import com.dh.proyecto.Repository.ORM.iTurno_repository;
import com.dh.proyecto.Services.iServices;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("turno_services")
public class TurnoService implements iServices<Turno> {

    public static Logger logger = Logger.getLogger(TurnoService.class);
    private iTurno_repository turno_repository;
    private iServices<Paciente> pacienteServices;
    private iServices<Odontologo> odontologoServices;

    @Autowired
    public void setTurno_repository(iTurno_repository turno_repository) {
        this.turno_repository = turno_repository;
    }

    @Autowired
    @Qualifier("paciente_services")
    public void setPaciente_services(iServices<Paciente> paciente_services) {
        this.pacienteServices = paciente_services;
    }

    @Autowired
    @Qualifier("odontologo_services")
    public void setOdontologo_services(iServices<Odontologo> odontologo_services) {
        this.odontologoServices = odontologo_services;
    }

    public boolean guardar(Turno turno) throws BadRequestException, NotFoundException {
        boolean res = false;
        if (pacienteServices.buscar(turno.getPaciente().getId()) == null) throw new NotFoundException("No existe paciente en la base de datos");
        if (odontologoServices.buscar(turno.getOdontologo().getId()) == null) throw new NotFoundException("No existe odontologo en la base de datos");
        if (!isTurnoAvailable(turno)) throw new BadRequestException("Ya existe turno registrado para el odontologo seleccionado en la fecha y hora seleccionada");
        try {
            turno_repository.save(turno);
            res = true;
        } catch (Exception e) {
            logger.error(e);
        }
        return res;
    }

    public boolean eliminar(Long id) {
        boolean res = false;
        try {
            turno_repository.deleteById(id);
            res = true;
        } catch (Exception e) {
            logger.info(e);
        }
        return res;
    }

    public Turno buscar(Long id) {
        Turno t = null;
        if (turno_repository.findById(id).isPresent()) t = turno_repository.findById(id).get();
        return t;
    }

    public List<Turno> listarTodos() {
        return turno_repository.findAll();
    }

    public boolean isTurnoAvailable(Turno t) {
        boolean res = true;
        Odontologo odontologo = odontologoServices.buscar(t.getOdontologo().getId());
        for (Turno turno_reservado:odontologo.getTurnos()) {
            if (t.getFecha_y_hora().equals(turno_reservado.getFecha_y_hora())) {
                res = false;
                break;
            }
        }
        return res;
    }
    public boolean isTurnoAvailable(Odontologo o, LocalDateTime t) {
        boolean res = true;
        Odontologo odontologo = odontologoServices.buscar(o.getId());
        if (odontologo != null) {
            for (Turno turno_reservado:odontologo.getTurnos()) {
                if (t.equals(turno_reservado.getFecha_y_hora())) {
                    res = false;
                    break;
                }
            }
        }
        return res;
    }
}
