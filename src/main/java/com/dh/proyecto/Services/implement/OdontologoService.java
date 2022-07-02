package com.dh.proyecto.Services.implement;


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

    public boolean guardar(Odontologo odoncito) {
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

        return odontologo_repository.findById(id).get();
    }

    public List<Odontologo> listarTodos() {
        List<Odontologo> result = odontologo_repository.findAll();
        return result;
    }
}
