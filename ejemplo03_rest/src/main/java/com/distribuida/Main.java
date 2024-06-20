package com.distribuida;

import com.distribuida.db.Persona;
import com.distribuida.servicios.ServicioPersona;
import com.google.gson.Gson;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import spark.Request;
import spark.Response;

import java.util.List;

import static spark.Spark.*;

public class Main {

    static SeContainer container;

    static List<Persona> listarPersonas(Request rq, Response rs){
        rs.type("application/json");

        var servicio = container.select(ServicioPersona.class).get();

        return servicio.allFind();
    }

    static Persona buscarPersona(Request rq, Response rs){
        rs.type("application/json");

        String _id = rq.params(":id");

        var servicio = container.select(ServicioPersona.class).get();

        var persona = servicio.findbyId(Integer.valueOf(_id));

        if(persona == null){

            halt(404, "Persona no encontrada");

        }

        return persona;
    }

    static Object eliminarPersona(Request rq, Response rs){
        rs.type("application/json");

        var _id = rq.params(":id");

        var servicio = container.select(ServicioPersona.class).get();

        var obj = servicio.findbyId(Integer.valueOf(_id));

        if(obj == null){
            halt(404, "Persona no encontrada");
        }

        servicio.remove(Integer.valueOf(_id));

        return "Persona eliminada";
    }

    static Object insertarPersona(Request rq, Response rs){
        rs.type("application/json");
        Gson gson = new Gson();

        Persona nuevaPersona = gson.fromJson(rq.body(), Persona.class);

        if(nuevaPersona == null){
            halt(400, "Datos de la persona invalidos");
        }

        var servicio = container.select(ServicioPersona.class).get();
        servicio.insert(nuevaPersona);

        rs.status(201);
        return  gson.toJson(nuevaPersona);


    }

    static Object actualizarPersona(Request rq, Response rs){
        rs.type("application/json");

        Gson gson = new Gson();
        Persona personaActualizada = gson.fromJson(rq.body(), Persona.class);

        if(personaActualizada == null){
            halt(400, "Datos de la persona invalidos");
        }

        var servicio = container.select(ServicioPersona.class).get();

        var personaExistente = servicio.findbyId(personaActualizada.getId());

        if(personaExistente == null){
            halt(404, "Persona no encontrada");
        }

        servicio.update(personaActualizada);

        return gson.toJson(personaActualizada);

    }

    public static void main(String[] args) {

        container = SeContainerInitializer
                .newInstance()
                .initialize();

        ServicioPersona sp = container.select(ServicioPersona.class).get();

        sp.allFind().stream().map(Persona::getNombre).forEach(System.out::println);

        Gson gson = new Gson();

        //puerto
        port(8080);

        get("/personas", Main::listarPersonas, gson::toJson);
        get("/personas/:id", Main::buscarPersona, gson::toJson);
        delete("/personas/:id", Main::eliminarPersona, gson::toJson);
        post("/personas", Main::insertarPersona, gson::toJson);
        put("/personas", Main::actualizarPersona, gson::toJson);
    }
}