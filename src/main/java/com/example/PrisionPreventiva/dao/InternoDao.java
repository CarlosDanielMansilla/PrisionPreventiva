package com.example.PrisionPreventiva.dao;

import com.example.PrisionPreventiva.models.Interno;

import java.util.List;

public interface InternoDao {

    Interno getInterno(Long id);
    List<Interno> getInternos();
    void eliminar(Long id);

    void agregar(Interno interno);

    void editar(Interno interno, Long id);
}
