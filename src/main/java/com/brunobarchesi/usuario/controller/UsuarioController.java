package com.brunobarchesi.usuario.controller;


import com.brunobarchesi.usuario.business.UsuarioService;
import com.brunobarchesi.usuario.business.dto.UsuarioDTO;
import com.brunobarchesi.usuario.infrastucture.entity.Usuario;
import com.brunobarchesi.usuario.infrastucture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor

public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")//metodo que vai tratar requisicoes POST body no /usuario/login
    public String login(@RequestBody UsuarioDTO usuarioDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDto.getEmail(),
                        usuarioDto.getSenha())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
        // Autentica email e senha com Spring Security
        // Se válido, gera e retorna um token JWT para o usuário usando o getName() que é
        //um metodo interno da classe automatico, nesse caso o getName usa o email pq passei o email
    }

    @GetMapping
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail(email));
        //Esse metodo de obter Usuario por email é passado pelo parametro chamado email na url com o email
    }


    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email) {
        usuarioService.deletarPorEmail(email);
        return ResponseEntity.ok().build();

    }
}
