package com.distribuida.service;

import com.distribuida.db.Persona;
import com.distribuida.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Persona findbyId(Integer id) {
        return personaRepository.findbyId(id);
    }

    @Override
    public List<Persona> allFind() {
        return personaRepository.allFind();
    }

    @Override
    public void insert(Persona obj) {
        personaRepository.insert(obj);
    }

    @Override
    public void update(Persona obj) {
        personaRepository.update(obj);
    }

    @Override
    public void remove(Integer id) {
        personaRepository.remove(id.intValue());
    }
}
