package com.dh.proyecto.Services.implement;


import com.dh.proyecto.Exceptions.BadRequestException;
import com.dh.proyecto.Models.entities.Odontologo;
import com.dh.proyecto.Repository.ORM.iOdontologo_repository;
import com.dh.proyecto.Services.iServices;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("odontologo_services")
public class OdontologoService implements iServices<Odontologo> {

    public static Logger logger = Logger.getLogger(OdontologoService.class);
    private iOdontologo_repository odontologo_repository;

    @Autowired
    public void setOdontologo_repository(iOdontologo_repository odontologo_repository) {
        this.odontologo_repository = odontologo_repository;
    }

    public boolean guardar(Odontologo odoncito) throws Exception {
        boolean res = false;
        try {
            odontologo_repository.save(odoncito);
            res = true;
        } catch (Exception e) {
            logger.info(e);
        }
        return res;
    }

    public boolean eliminar(Long id) {
        boolean res = false;
        try {
            odontologo_repository.deleteById(id);
            res = true;
        } catch (Exception e) {
            logger.info(e);
        }
        return res;
    }

    public Odontologo buscar(Long id) {
        Odontologo o = null;
        if (odontologo_repository.findById(id).isPresent()) o = odontologo_repository.findById(id).get();
        return o;
    }

    public List<Odontologo> listarTodos() {
        return odontologo_repository.findAll();
    }
}
