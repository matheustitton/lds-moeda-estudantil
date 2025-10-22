package com.moeda.estudantil.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class CriptografiaUtil {

    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    public static String criptografar(String senha) {
        return ENCODER.encode(senha);
    }
}
