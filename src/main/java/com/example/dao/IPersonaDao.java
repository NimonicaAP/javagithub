package com.example.dao;

import com.example.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
//Agrega los metodos read, insert, update y delete
public interface IPersonaDao extends JpaRepository<Persona, Long >{//Lave primaria es de tipo Long
    
}
