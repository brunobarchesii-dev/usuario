package com.brunobarchesi.usuario.controller;

import com.brunobarchesi.usuario.business.UsuarioService;
import com.brunobarchesi.usuario.business.dto.EnderecoDTO;
import com.brunobarchesi.usuario.business.dto.TelefoneDTO;
import com.brunobarchesi.usuario.business.dto.UsuarioDTO;
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

    private UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;




                        //metodos de cadastro, login, delete e get:

    //metodo de cadastro de usuario
    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }




    //Metodo de login, verificacao
    @PostMapping("/login")//metodo que vai tratar requisicoes POST body no /usuario/login
    public String login (@RequestBody UsuarioDTO usuarioDto){
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
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail(email));
        //Esse metodo de obter Usuario por email é passado pelo parametro chamado email na url com o email
        }






     @DeleteMapping("/{email}")
     public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email){
        usuarioService.deletarPorEmail(email);
        return ResponseEntity.ok().build(); //build() finaliza a resposta HTTP SEM corpo.
            //Nesse metodo deleto o usuario passando na url /usuario/email@dealguem com o verbo DELETE http.
        }


        /// ///////////////////////////////                 ////////////////////////////////////




                //ATUALIZACOES DE USUARIO, TELEFONE E ENDERECOS:

    //atualizando dados de usuario, menos endereco e telefone:
    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizadoDadoUsuario(@RequestBody UsuarioDTO usuarioDTO,
                                                            @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, usuarioDTO));
        //o RequestHeader diz que vou passar o token via header chamado authorization
        }



    //Atualizar endereco:
    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                                         @RequestParam("id") Long id){
        return ResponseEntity.ok(usuarioService.atualizarEndereco(id, enderecoDTO));

    }



    //Atualizar Telefone:
    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTO> atualizarTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                                         @RequestParam("id") Long id) {
        return ResponseEntity.ok(usuarioService.atualizarTelefone(id, telefoneDTO));

    }




    //CADASTRAR ENDERECO DE UM USUARIO:
    @PostMapping("/endereco")
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                                         @RequestHeader("Authorization") String token){
            return ResponseEntity.ok(usuarioService.cadastrarEndereco(enderecoDTO, token));
    }






    //CADASTRAR TELEFONE DE UM USUARIO:
    @PostMapping("/telefone")
    public ResponseEntity<TelefoneDTO> cadastrarTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                         @RequestHeader("Authorization") String token){
            return ResponseEntity.ok(usuarioService.cadastrarTelefone(telefoneDTO, token));


    }








    }

