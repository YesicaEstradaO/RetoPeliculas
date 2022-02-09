package com.plataformacine.demo.controller;

import com.plataformacine.demo.models.Usuario;
import com.plataformacine.demo.repo.UserRepo;
import com.plataformacine.demo.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UserRepo repo;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser( @RequestBody Usuario userData){

            System.out.println(userData);
        Usuario user=repo.findByEmail(userData.getEmail());
        if(user.getPassword().equals(userData.getPassword()))
            return ResponseEntity.ok(user);
    
        return (ResponseEntity<?>) ResponseEntity.internalServerError();

    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id){
        return new ResponseEntity<>(usuarioService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioEncontrado = usuarioService.findById(id);

        if (usuarioEncontrado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {

            usuarioEncontrado.setName(usuario.getName());
            usuarioEncontrado.setUsername(usuario.getUsername());
            usuarioEncontrado.setPassword(usuario.getPassword());
            usuarioEncontrado.setSurname(usuario.getSurname());
            usuarioEncontrado.setEmail(usuario.getEmail());

            return new ResponseEntity<>(usuarioService.save(usuarioEncontrado), HttpStatus.CREATED);

        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

