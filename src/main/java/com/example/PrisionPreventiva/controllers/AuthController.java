package com.example.PrisionPreventiva.controllers;


import com.example.PrisionPreventiva.dao.UsuarioDao;
import com.example.PrisionPreventiva.models.Usuario;
import com.example.PrisionPreventiva.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    JWTUtil jwtUtil;

    @RequestMapping(value= "app/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){

        Usuario logueado = usuarioDao.verificar(usuario);

        if(logueado != null){
            String token = jwtUtil.create(String.valueOf(logueado.getId()),logueado.getEmail());

            return token;
        }
        else{
            return "Fail";
        }
    }

    @RequestMapping(value= "app/usuarios", method = RequestMethod.POST)
    public void agregarUsuarios(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.agregarU(usuario);
    }
}
