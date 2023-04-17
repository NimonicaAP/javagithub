package com.example.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarPassword {

    public static void main(String[] args) {
        //Encriptar passwords
        var password = "123";
        System.out.println("password" + password);
        System.out.println("password encriptado:" + encriptarPassword(password));
    }

    //metodo para ecriptar
    public static String encriptarPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
