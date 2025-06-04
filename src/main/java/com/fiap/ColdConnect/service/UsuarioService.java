package com.fiap.ColdConnect.service;

import com.fiap.ColdConnect.model.Usuario;
import com.fiap.ColdConnect.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public Usuario autenticar(String email, String senha) {
        return repository.findByEmail(email)
                .filter(u -> encoder.matches(senha, u.getSenha()))
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
    }
}
