package com.brunobarchesi.usuario.business;

import com.brunobarchesi.usuario.business.converter.UsuarioConverter;
import com.brunobarchesi.usuario.business.dto.UsuarioDTO;
import com.brunobarchesi.usuario.infrastucture.entity.Usuario;
import com.brunobarchesi.usuario.infrastucture.execeptions.ConflictExeception;
import com.brunobarchesi.usuario.infrastucture.execeptions.ResourceNotFoundException;
import com.brunobarchesi.usuario.infrastucture.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder; //Injecao para criptografia de senha


    //salvar usuario:
    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        verificaEmail(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha())); //criptografando a senha
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
       return  usuarioDTO = usuarioConverter.paraUsuarioDTO(usuario);

    }





    //Metodos que verifica se o email passado ja existe:
    public void verificaEmail(String email){
        try{
            boolean existe = verificaEmailCadastrado(email);
            if (existe){
                throw new ConflictExeception("Email ja cadastrado no sistema" + email);
            }
        } catch (ConflictExeception e){
            throw new ConflictExeception("Email ja cadastrado no sistema" + e.getCause());
        }

    }
    public boolean verificaEmailCadastrado(String email){
        return usuarioRepository.existsByEmail(email);
    }





    //trazer usuario por email:
    public Usuario buscaUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email nao encontrado" + email));
        //orElseThrow é um jeito de usar try-catch de uma forma diferente.
    }




    //deletar por email:
    public void deletarPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }


}
