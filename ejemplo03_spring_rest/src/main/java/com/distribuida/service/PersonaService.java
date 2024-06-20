package com.distribuida.service;

import com.distribuida.db.Persona;

import java.util.List;

public interface PersonaService {
    Persona findbyId(Integer id);

    List<Persona> allFind();

    void insert(Persona obj);

    void update(Persona obj);

    void remove(Integer id);
}
