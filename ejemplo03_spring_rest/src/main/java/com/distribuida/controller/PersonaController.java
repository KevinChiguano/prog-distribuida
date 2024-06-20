package com.distribuida.controller;

import com.distribuida.db.Persona;
import com.distribuida.service.PersonaService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    public Object findById(spark.Request req, spark.Response res){
        Integer id = Integer.valueOf(req.params(":id"));
        return personaService.findbyId(id);
    }

    public Object findAll(spark.Request req, spark.Response res){
        return personaService.allFind();
    }

    public Object create(spark.Request req, spark.Response res){
        Persona persona = new Gson().fromJson(req.body(), Persona.class);
        personaService.insert(persona);
        return "Persona creada exitosamente";
    }

    public Object update(spark.Request req, spark.Response res){
        Integer id = Integer.valueOf(req.params(":id"));
        Persona persona = new Gson().fromJson(req.body(), Persona.class);
        persona.setId(id);
        personaService.update(persona);
        return "Persona actualizada exitosamente";
    }

    public Object delete(spark.Request req, spark.Response res){
        Integer id = Integer.valueOf(req.params(":id"));
        personaService.remove(id);
        return "Persona eliminada exitosamente";
    }
}