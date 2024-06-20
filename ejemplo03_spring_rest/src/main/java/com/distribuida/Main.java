package com.distribuida;



import com.distribuida.db.Persona;
import com.distribuida.service.PersonaService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;

import java.util.List;

import static spark.Spark.*;

/*public class Main {

    public static void main(String[] args) {
        // Inicializar el contexto de Spring
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Obtener el bean PersonaController
        PersonaController personaController = context.getBean(PersonaController.class);

        // Configurar las rutas de Spark Java
        get("/personas/:id", personaController::findById);
        get("/personas", personaController::findAll);
        post("/personas", personaController::create);
        put("/personas/:id", personaController::update);
        delete("/personas/:id", personaController::delete);
    }

}*/


public class Main {



    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PersonaService personaService = context.getBean(PersonaService.class);


        port(8080);

        get("/personas", (req, res) -> {
            List<Persona> personas = personaService.allFind();
            return new Gson().toJson(personas); // Convertir la lista de personas a JSON
        });

        get("/personas/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Persona persona = personaService.findbyId(id);
            if (persona != null) {
                return new Gson().toJson(persona); // Convertir la persona a JSON
            } else {
                res.status(404); // Persona no encontrada
                return "Persona no encontrada";
            }
        });

        post("/personas", (req, res) -> {
            Gson gson = new Gson();
            Persona nuevaPersona = gson.fromJson(req.body(), Persona.class);
            personaService.insert(nuevaPersona);
            res.status(201); // Persona creada correctamente
            return "Persona creada correctamente";
        });

        put("/personas/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Gson gson = new Gson();
            Persona persona = gson.fromJson(req.body(), Persona.class);
            persona.setId(id);
            personaService.update(persona);
            return "Persona actualizada correctamente";
        });

        delete("/personas/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            personaService.remove(id);
            return "Persona eliminada correctamente";
        });

        // Guardar una persona en la base de datos
        /*Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre("Kevin");
        nuevaPersona.setEdad(30);
        nuevaPersona.setDireccion("Ecuatoriana");
        personaService.insert(nuevaPersona);

        // Obtener la persona por su ID
        Persona personaRecuperada = personaService.findbyId(1);
        System.out.println("Persona recuperada: " + personaRecuperada);*/
    }
}