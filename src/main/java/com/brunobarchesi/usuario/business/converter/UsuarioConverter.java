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
                .enderecos(usuarioDTO.getEnderecosDTO() != null ? paraListaEndereco(usuarioDTO.getEnderecosDTO()) : null) //preciso criar metodo
                //conersor da lista de enderecos primeiro, esta abaixo
                .telefones(usuarioDTO.getTelefonesDTO() != null ? paraListaTelefone(usuarioDTO.getTelefonesDTO()) : null)//preciso criar metodo
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
                .id(enderecoDTO.getId())
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
        telefone.setId(telefoneDTO.getId());
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
                .enderecosDTO(usuario.getEnderecos() != null ? paraListaEnderecoDTO(usuario.getEnderecos()) : null) //preciso criar metodo
                //conersor da lista de enderecos primeiro, esta abaixo
                .telefonesDTO(usuario.getTelefones() != null ? paraListaTelefoneDTO(usuario.getTelefones()) : null)//preciso criar metodo
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



    //Trasnformando um unico endereco em EnderecoDTO para depois passar para o metodo acima:
    public EnderecoDTO paraEnderecoDTO(Endereco endereco){
        return EnderecoDTO.builder()
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .id(endereco.getId())
                .build();
    }





    //conversao de telefones sem builder e sem stream:

    //Transformando uma lista de telefones em telefoneDTO SEM O STREAM:
    public List<TelefoneDTO> paraListaTelefoneDTO (List<Telefone> telefones){
        List<TelefoneDTO> listaTelefonesDTO = new ArrayList<>();
        for (Telefone telefone : telefones ){
            listaTelefonesDTO.add(paraTelefoneDTO(telefone));
        }

        return listaTelefonesDTO;
    }


    //Transformando primeiro um unico Telefone em telefoneDTO sem usar o builder:
    public TelefoneDTO paraTelefoneDTO(Telefone telefone){
        TelefoneDTO telefoneDTO = new TelefoneDTO();
        telefoneDTO.setNumero(telefone.getNumero());
        telefoneDTO.setDdd(telefone.getDdd());
        telefoneDTO.setId(telefone.getId());
        return telefoneDTO;
    }









    //METODO DE UPDATE DE USUARIO, SEM MEXER EM ENDERECOS E TELEFONES:
    public Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario usuario){
        return Usuario.builder()
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : usuario.getNome())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : usuario.getEmail())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : usuario.getSenha())
                .enderecos(usuario.getEnderecos())
                .telefones(usuario.getTelefones())
                .build();

        //Utilizei operador ternario IF, se for diferente de null o dto passo, ou seja, o usuario atualizou tal dado
        //atualiza, se o dto passado no campo for null o usuario n quer atualizar esse campo entao puxa
        //o valor da entity mesmo.
    }






    //METODO PARA ATUALIZAR ENDERECO:
    public Endereco atualizarEndereco(EnderecoDTO enderecoDTO, Endereco endereco){
        return Endereco.builder()
                .id(endereco.getId())
                .rua(enderecoDTO.getRua() != null ? enderecoDTO.getRua() : endereco.getRua())
                .estado(enderecoDTO.getEstado() != null ? enderecoDTO.getEstado() : endereco.getEstado())
                .numero(enderecoDTO.getNumero() != null ? enderecoDTO.getNumero() : endereco.getNumero())
                .cidade(enderecoDTO.getCidade() != null ?  enderecoDTO.getCidade() : endereco.getCidade())
                .cep(enderecoDTO.getCep() != null ? enderecoDTO.getCep() : endereco.getCep())
                .complemento(enderecoDTO.getComplemento() != null ? enderecoDTO.getComplemento() : endereco.getComplemento())
                .build();
    }






    //Metodo para atualizar TELEFONE:
    public Telefone atualizarTelefone (TelefoneDTO telefoneDTO, Telefone telefone){
        return Telefone.builder()
                .id(telefone.getId())
                .ddd(telefoneDTO.getDdd() != null ? telefoneDTO.getDdd() : telefone.getDdd())
                .numero(telefoneDTO.getNumero() != null ? telefoneDTO.getNumero() : telefone.getNumero())
                .build();
    }






    //Metodo para transformar um enderecoDTO em entity para cadastro:
    public Endereco paraEnderecoEntity(EnderecoDTO enderecoDTO, Long idUsuario){
        return Endereco.builder()
                .cidade(enderecoDTO.getCidade())
                .rua(enderecoDTO.getRua())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .numero(enderecoDTO.getNumero())
                .estado(enderecoDTO.getEstado())
                .usuario_id(idUsuario)
                .build();
    }






    //Metodo para trasnformar telefonedto em entity para cadastro:
    public Telefone paraTelefoneEntity (TelefoneDTO telefoneDTO, Long idUusario){
        return  Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .usuario_id(idUusario)
                .build();



    }





}
