package com.plataformacine.demo.services;

import com.plataformacine.demo.models.Usuario;

import java.util.List;

public interface UsuarioService {

    public Usuario save(Usuario usuario);

    public Usuario findById(Long id);

    public List<Usuario> findAll();

    public void delete(Long id);

}
