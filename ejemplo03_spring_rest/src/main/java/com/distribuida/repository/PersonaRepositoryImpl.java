package com.distribuida.repository;

import com.distribuida.db.Persona;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PersonaRepositoryImpl implements PersonaRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Persona findbyId(Integer id) {
        return em.find(Persona.class, id);
    }

    @Override
    public List<Persona> allFind() {
        Query query = em.createQuery("select p from Persona p order by id asc");
        return query.getResultList();
    }

    @Override
    public void insert(Persona obj) {
        em.persist(obj);
    }

    @Override
    public void update(Persona obj) {
        em.merge(obj);
    }

    @Override
    public void remove(Integer id) {
        em.remove(this.findbyId(id));
    }
}
