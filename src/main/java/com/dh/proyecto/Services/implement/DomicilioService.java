package com.dh.proyecto.Services.implement;


import com.dh.proyecto.Exceptions.BadRequestException;
import com.dh.proyecto.Models.entities.Domicilio;
import com.dh.proyecto.Repository.ORM.iDomicilio_repository;
import com.dh.proyecto.Services.iServices;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioService implements iServices<Domicilio> {

    public static Logger logger = Logger.getLogger(DomicilioService.class);

    private iDomicilio_repository domicilio_repositoy;

    @Autowired
    public void setDomicilio_repositoy(iDomicilio_repository domicilio_repositoy) {
        this.domicilio_repositoy = domicilio_repositoy;
    }

    public boolean guardar(Domicilio dom) throws Exception {
        boolean res = false;
        try {
            domicilio_repositoy.save(dom);
            res = true;
        } catch (Exception e) {
            logger.info(e);
        }
        return res;
    }

    public boolean eliminar(Long id) {
        boolean res = false;
        try {
            domicilio_repositoy.deleteById(id);
            res = true;
        } catch (Exception e) {
            logger.info(e);
        }
        return res;
    }

    public Domicilio buscar(Long id) {
        return domicilio_repositoy.findById(id).get();
    }

    public List<Domicilio> listarTodos() {
        return domicilio_repositoy.findAll();
    }
}
