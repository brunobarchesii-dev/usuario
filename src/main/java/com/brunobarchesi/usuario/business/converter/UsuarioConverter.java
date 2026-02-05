package com.brunobarchesi.usuario.business.converter;


import com.brunobarchesi.usuario.business.dto.EnderecoDTO;
import com.brunobarchesi.usuario.business.dto.TelefoneDTO;
import com.brunobarchesi.usuario.business.dto.UsuarioDTO;
import com.brunobarchesi.usuario.infrastucture.entity.Endereco;
import com.brunobarchesi.usuario.infrastucture.entity.Telefone;
import com.brunobarchesi.usuario.infrastucture.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioConverter {

    //DE USUARIODTO PARA USUARIO:

    public Usuario paraUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefone(usuarioDTO.getTelefones()))
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecosDTO) {
        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoDTO enderecoDTO : enderecosDTO) {
            enderecos.add(paraEndereco(enderecoDTO));
        }
        return enderecos;
    }


    public Endereco paraEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .cep(enderecoDTO.getCep())
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .complemento(enderecoDTO.getComplemento())
                .build();
    }

    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefonesDTO) {
        return telefonesDTO.stream().map(this::paraTelefone).toList(); //Outro jeito de fazer sem o loop for
        //que fiz no paraListaEndereco
        //Para cada objeto na lista telefonesDTO, mapeie para o metodo paraTelefone e retorne uma lista deles
    }


    public Telefone paraTelefone(TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .build();
    }


    //DE USUARIO PARA USUARIODTO:


    public UsuarioDTO paraUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(paraListaEnderecoDTO(usuario.getEnderecos()))
                .telefones(paraListaTelefoneDTO(usuario.getTelefones()))
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecos) {
        List<EnderecoDTO> enderecosDTO = new ArrayList<>();
        for (Endereco endereco : enderecos) {
            enderecosDTO.add(paraEnderecoDTO(endereco));
        }
        return enderecosDTO;
    }


    public EnderecoDTO paraEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .cep(endereco.getCep())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .complemento(endereco.getComplemento())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefones) {
        return telefones.stream().map(this::paraTelefoneDTO).toList(); //Outro jeito de fazer sem o loop for
        //que fiz no paraListaEndereco
        //Para cada objeto na lista telefonesDTO, mapeie para o metodo paraTelefone e retorne uma lista deles
    }


    public TelefoneDTO paraTelefoneDTO(Telefone telefone) {
        return TelefoneDTO.builder()
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();
    }

    //Metodo ternario update de Usuario
    public Usuario updateUsuario (UsuarioDTO usuarioDTO, Usuario usuarioEntity){
        Usuario.builder()
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : usuarioEntity.getNome())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : usuarioEntity.getEmail())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : usuarioEntity.getSenha())
                .id(usuarioEntity.getId()) //dificilmente é mudado entao ja pego direto da entity
                .enderecos(usuarioEntity.getEnderecos())//Aqui nesse metodo n vou mudar endereco entao pego oq ja tem
                .telefones(usuarioEntity.getTelefones()) //Aqui nesse metodo n vou mudar telefone entao pego oq ja tem
                .build();


    }











}