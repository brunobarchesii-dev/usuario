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


    //DETALHE: PARA USAR O BUILDER MARQUEI AS ENTITYS COM @BUILDER
    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecosDTO())) //preciso criar metodo
                //conersor da lista de enderecos primeiro, esta abaixo
                .telefones(paraListaTelefone(usuarioDTO.getTelefonesDTO()))//preciso criar metodo
                //conersor da lista de telefones primeiro, esta abaixo

                .build();
    }




    //CONVERSAO DE ENDERECOS COM BUILDER E COM STREAM:

    //Transformando uma lista de enderecosDTO em Endereco usando STREAM:
    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS){
        return  enderecoDTOS.stream().map(this::paraEndereco).toList();
        //para cada endereoDTO na lista de enderecosDTO, mapeie cada um deles para o metodo
        //abaixo chamado paraEndereco e depois adicione em uma lista
    }



    //Trasnformando um unico enderecoDTO em Endereco para depois passar para o metodo acima:
    public Endereco paraEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .build();
    }





    //conversao de telefones sem builder e sem stream:

    //Transformando uma lista de telefonesDTO em telefone Entity SEM O STREAM:
    public List<Telefone> paraListaTelefone (List<TelefoneDTO> telefoneDTOS){
        List<Telefone> listaTelefones = new ArrayList<>();
        for (TelefoneDTO telefoneDTO : telefoneDTOS ){
            listaTelefones.add(paraTelefone(telefoneDTO));
        }

        return listaTelefones;
    }


    //Transformando primeiro um unico TelefoneDTO em telefone Entity sem usar o builder:
    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        Telefone telefone = new Telefone();
        telefone.setNumero(telefoneDTO.getNumero());
        telefone.setDdd(telefoneDTO.getDdd());
        return telefone;
    }





    /// //////////////////////////////////////////

    //TRANSFORMANDO USUARIO ENTITY EM USUARIODTO:



    //DETALHE: PARA USAR O BUILDER MARQUEI OS DTOs COM @BUILDER
    public UsuarioDTO paraUsuarioDTO(Usuario usuario){
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecosDTO(paraListaEnderecoDTO(usuario.getEnderecos())) //preciso criar metodo
                //conersor da lista de enderecos primeiro, esta abaixo
                .telefonesDTO(paraListaTelefoneDTO(usuario.getTelefones()))//preciso criar metodo
                //conersor da lista de telefones primeiro, esta abaixo

                .build();
    }




    //CONVERSAO DE ENDERECOS COM BUILDER E COM STREAM:

    //Transformando uma lista de enderecosDTO em Endereco usando STREAM:
    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecos){
        return  enderecos.stream().map(this::paraEnderecoDTO).toList();
        //para cada endereoDTO na lista de enderecosDTO, mapeie cada um deles para o metodo
        //abaixo chamado paraEndereco e depois adicione em uma lista
    }



    //Trasnformando um unico enderecoDTO em Endereco para depois passar para o metodo acima:
    public EnderecoDTO paraEnderecoDTO(Endereco endereco){
        return EnderecoDTO.builder()
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .build();
    }





    //conversao de telefones sem builder e sem stream:

    //Transformando uma lista de telefonesDTO em telefone Entity SEM O STREAM:
    public List<TelefoneDTO> paraListaTelefoneDTO (List<Telefone> telefones){
        List<TelefoneDTO> listaTelefonesDTO = new ArrayList<>();
        for (Telefone telefone : telefones ){
            listaTelefonesDTO.add(paraTelefoneDTO(telefone));
        }

        return listaTelefonesDTO;
    }


    //Transformando primeiro um unico TelefoneDTO em telefone Entity sem usar o builder:
    public TelefoneDTO paraTelefoneDTO(Telefone telefone){
        TelefoneDTO telefoneDTO = new TelefoneDTO();
        telefoneDTO.setNumero(telefone.getNumero());
        telefoneDTO.setDdd(telefone.getDdd());
        return telefoneDTO;
    }





}
