package com.dh.proyecto.Repository.DAO;

import java.util.HashMap;

public interface iDao<T> {

    boolean guardar(T t);
    boolean eliminar(Long id);
    T buscar(Long id);
    HashMap<Long, T> listar();

}
