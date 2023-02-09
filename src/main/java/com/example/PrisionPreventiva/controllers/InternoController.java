package com.example.PrisionPreventiva.controllers;

import com.example.PrisionPreventiva.dao.InternoDao;
import com.example.PrisionPreventiva.models.Interno;
import com.example.PrisionPreventiva.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class InternoController {

    @Autowired
    private InternoDao internoDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value= "app/interno/{id}")
    public Interno getInterno(@PathVariable Long id){
        return internoDao.getInterno(id);
    }

    @RequestMapping(value= "app/internos")
    public List<Interno> getInternos(@RequestHeader(value="Authorization") String token){

        if(!validarToken(token)){
            return null;
        }

        return internoDao.getInternos();
    }

    private boolean validarToken (String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value= "app/internos", method = RequestMethod.POST)
    public void agregarInternos(@RequestHeader(value="Authorization") String token, @RequestBody Interno interno){
        if(!validarToken(token)){
            return;
        }
        internoDao.agregar(interno);
    }

    @RequestMapping(value= "app/interno/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value="Authorization") String token, @PathVariable Long id){
        if(!validarToken(token)){
            return;
        }
        internoDao.eliminar(id);
    }

    @RequestMapping(value= "app/interno/{id}", method = RequestMethod.PUT)
    public void editar(@RequestHeader(value="Authorization") String token, @RequestBody Interno interno,@PathVariable Long id){
        if(!validarToken(token)){
            return;
        }
        internoDao.editar(interno, id);
    }
}
