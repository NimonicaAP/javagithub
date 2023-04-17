package com.example.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;



@Entity
@Data
@Table(name="usuario2")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long idUsuario;
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;
    
    //Mapeo de usuario a rol ya que un usuario puede tener multiples roles
    @OneToMany
    @JoinColumn(name="id_usuario")
    //Definir un atributo de lista de objetos de tipo rol
    private List<Rol> roles;
    
    
}
