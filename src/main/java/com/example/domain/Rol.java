package com.example.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data //(lombok get set, equals hashset)
@Table(name="rol")
public class Rol implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id//Llave primaria
    @GeneratedValue(strategy=GenerationType.IDENTITY)//Generacion de llave primaria , identity cuando es autoincrementable
    private Long idRol;
    
    @NotEmpty //Obliga a que se rellene con la información
    private String nombre;
}
