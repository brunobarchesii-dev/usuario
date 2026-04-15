package com.brunobarchesi.usuario.business;

import com.brunobarchesi.usuario.business.converter.UsuarioConverter;
import com.brunobarchesi.usuario.business.dto.UsuarioDTO;
import infrastucture.entity.Usuario;
import infrastucture.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
       return  usuarioDTO = usuarioConverter.paraUsuarioDTO(usuario);

    }



}
