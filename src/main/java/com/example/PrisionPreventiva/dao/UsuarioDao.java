package com.example.PrisionPreventiva.dao;


import com.example.PrisionPreventiva.models.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsuario();
    void agregarU(Usuario usuario);
    Usuario verificar(Usuario usuario);
}
