package com.plataformacine.demo.repo;

import com.plataformacine.demo.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Usuario, String>{

    Usuario findByEmail(String email);
    
}
