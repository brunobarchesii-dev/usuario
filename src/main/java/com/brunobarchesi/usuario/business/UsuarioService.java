package com.brunobarchesi.usuario.business;

import com.brunobarchesi.usuario.business.converter.UsuarioConverter;
import com.brunobarchesi.usuario.business.dto.UsuarioDTO;
import com.brunobarchesi.usuario.infrastucture.exceptions.ConflictExeception;
import com.brunobarchesi.usuario.infrastucture.entity.Usuario;
import com.brunobarchesi.usuario.infrastucture.exceptions.ResourceNotFoundException;
import com.brunobarchesi.usuario.infrastucture.repository.UsuarioRepository;
import com.brunobarchesi.usuario.infrastucture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        verificaEmail(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO); //convertendo usuarioDTO para usuario entity
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }


    public void verificaEmail(String email){
        try {
            boolean existe =  verificaEmailCadastrado(email);
            if (existe){
                throw new ConflictExeception("Email ja cadastrado no sistema!");
            }
        }catch (ConflictExeception e){
            System.out.println("Email ja cadastradi no sistema" + e.getMessage());
        }
    }

    public boolean verificaEmailCadastrado(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscaUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email nao encontrado" + email));
        //orElseThrow é um jeito de usar try-catch de uma forma diferente.
    }

    public void deletarPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }


    //METODO QUE ATUALIZA DADOS DE USUARIO. Procurando o email ATRAVÉS DO TOKEN passado com um metodo que faz isso que tem no
    //jwtutil chamado extractemail que extrai o email do token, assim sei qual usuario quer alterar dados.
    //Para tirar a obrigatoriedade de passar email
    public UsuarioDTO atualizarDadosUsuario (String token, UsuarioDTO usuarioDTO) {
        String email = jwtUtil.extractEmail(token.substring(7)); //metodo que extrai email e tira o bearer e deixa só o token

        //criptografia de senha
        usuarioDTO.setSenha(usuarioDTO.getSenha() != null ? passwordEncoder.encode(usuarioDTO.getSenha()) : null);

        //buscar os dados do usuario no banco de dados:
        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email nao enocntrado"));

        //mesclamo os dados recebidos novos com os dados no banco de dados
        Usuario usuario = usuarioConverter.updateUsuario(usuarioDTO, usuarioEntity);

        //Salvou os dados do usuario convertido, pegou o retorno e convertou para usuarioDTO
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));

    }


}
