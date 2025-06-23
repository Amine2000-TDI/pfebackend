package com.ezzahi.pfe_backend.services;

import java.util.List;

public interface Crudservice<T,D> {
    D save(D dto);
    void delete(Long id);
    D getById(Long id);
    List<D> getAll();
}
