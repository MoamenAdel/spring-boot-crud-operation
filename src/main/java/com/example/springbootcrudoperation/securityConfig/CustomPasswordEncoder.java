package com.example.springbootcrudoperation.securityConfig;


import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return HashingUtils.sha256(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence plain, String hashed) {
        return HashingUtils.sha256(plain.toString()).equals(hashed)
                || plain.toString().equals(hashed);
    }
}
