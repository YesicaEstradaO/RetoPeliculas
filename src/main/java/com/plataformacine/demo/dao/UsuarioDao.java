package com.plataformacine.demo.dao;

import com.plataformacine.demo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface UsuarioDao extends JpaRepository<Usuario, Serializable> {

}
