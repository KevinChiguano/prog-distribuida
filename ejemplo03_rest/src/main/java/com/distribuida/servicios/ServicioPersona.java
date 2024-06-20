package com.distribuida.servicios;

import com.distribuida.db.Persona;

import java.util.List;

public interface ServicioPersona {

    Persona findbyId(Integer id);

    List<Persona> allFind();

    void insert(Persona obj);

    void update(Persona obj);

    void remove(Integer id);
}
