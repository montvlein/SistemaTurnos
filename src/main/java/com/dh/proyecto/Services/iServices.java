package com.dh.proyecto.Services;

import java.util.List;

public interface iServices<T> {

    boolean guardar(T t);

    boolean eliminar(Long id);

    T buscar(Long id);

    List<T> listarTodos();
}
