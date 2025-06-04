package com.fiap.ColdConnect.controller;

import com.fiap.ColdConnect.dto.AuthRequestDTO;
import com.fiap.ColdConnect.dto.AuthResponseDTO;
import com.fiap.ColdConnect.model.Usuario;
import com.fiap.ColdConnect.repository.UsuarioRepository;
import com.fiap.ColdConnect.security.JwtService;
import com.fiap.ColdConnect.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        Usuario user = usuarioService.autenticar(request.getEmail(), request.getSenha());
        String token = jwtService.gerarToken(user.getEmail());
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequestDTO request) {
        // AQUI você usa o objeto injetado usuarioRepository, não a classe diretamente
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado!");
        }

        Usuario novoUsuario = Usuario.builder()
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getSenha()))
                .build();

        usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }
}
