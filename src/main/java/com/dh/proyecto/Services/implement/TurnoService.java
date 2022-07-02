package com.dh.proyecto.Services.implement;

import com.dh.proyecto.Models.entities.Turno;
import com.dh.proyecto.Repository.ORM.iTurno_repository;
import com.dh.proyecto.Services.iServices;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("turno_services")
public class TurnoService implements iServices<Turno> {

    public static Logger logger = Logger.getLogger(TurnoService.class);
    private iTurno_repository turno_repository;

    @Autowired
    public void setTurno_repository(iTurno_repository turno_repository) {
        this.turno_repository = turno_repository;
    }

    public boolean guardar(Turno paz) {
        boolean res = false;
        try {
            if (turno_repository.save(paz) != null) res = true;
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
        return turno_repository.findById(id).get();
    }

    public List<Turno> listarTodos() {
        List<Turno> result = turno_repository.findAll();
        return result;
    }
}
