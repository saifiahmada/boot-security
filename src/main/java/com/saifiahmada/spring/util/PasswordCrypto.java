package com.saifiahmada.spring.util;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordCrypto {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static PasswordCrypto instance;

    public static PasswordCrypto getInstance() {
        if(instance == null) {
            instance = new PasswordCrypto();
        }
        return instance;
    }

    public String encrypt(String str) {
    	System.out.println("str password = " + str); 
        return passwordEncoder.encode(str);
    }
}