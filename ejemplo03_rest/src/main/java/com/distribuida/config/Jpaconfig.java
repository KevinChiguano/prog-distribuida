package com.distribuida.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

//Componente cdi
@ApplicationScoped
public class Jpaconfig {

    EntityManagerFactory emf;

    @PostConstruct
    public void init(){
        System.out.println("**** JPA CONFIG::init");
        emf = Persistence.createEntityManagerFactory("pu-distribuida");
    }

    @Produces
    public EntityManager em(){
        System.out.println("**** JP CONFIG::em");
        return emf.createEntityManager();
    }
}
