package com.example.PrisionPreventiva.dao;

import com.example.PrisionPreventiva.models.Interno;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class InternoDaoImp implements InternoDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Interno getInterno(Long id) {
        return entityManager.find(Interno.class,id);
    }
    @Override
    @Transactional
    public List<Interno> getInternos() {
        String query = "FROM Interno";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Interno interno = entityManager.find(Interno.class, id);
        entityManager.remove(interno);
    }

    @Override
    public void agregar(Interno interno) {
        entityManager.merge(interno);
    }

    @Transactional
    @Override
    public void editar(Interno interno,Long id) {
        Interno interno2 = entityManager.find(Interno.class, id);

        if(interno.getCircunscripcion() != null){
            interno2.setCircunscripcion(interno.getCircunscripcion());
        }

        if(interno.getFiscalia() != null){
            interno2.setFiscalia(interno.getFiscalia());
        }

        if(interno.getNombreCompleto() != null)
        {
            interno2.setNombreCompleto(interno.getNombreCompleto());
        }

        if(interno.getExpediente() != null){
            interno2.setExpediente(interno.getExpediente());
        }

        if(interno.getFechaDetenido() != null){
            interno2.setFechaDetenido(interno.getFechaDetenido());
        }

        if(interno.getFechaLimite() != null){
            interno2.setFechaLimite(interno.getFechaLimite());
        }

        if(interno.getFechaPreventiva() != null){
            interno2.setFechaPreventiva(interno.getFechaPreventiva());
        }

        if(interno.getObservaciones() != null){
            interno2.setObservaciones(interno.getObservaciones());
        }

        entityManager.merge(interno2);
    }
}
