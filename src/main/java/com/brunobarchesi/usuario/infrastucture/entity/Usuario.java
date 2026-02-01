package com.brunobarchesi.usuario.infrastucture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity //Informo que essa classe É UMA TABELA
@Table(name="Usuario") //caso eu nao indique o nome da tabela ele usa o nome da classe como padrao.
public class Usuario implements UserDetails { //o Spring Security só sabe autenticar objetos que implementam UserDetails

    @Id //Aqui digo que o atributo abaixo é um identificador unico
    @GeneratedValue(strategy = GenerationType.IDENTITY) // aqui digo pra gerar automaticamente o Id.
    private Long id;
    @Column(name = "nome", length = 100) //Aqui passo a primeia coluna da tabela
    private String nome;
    @Column(name = "email", length = 100) //Nao preciso passar lenght se nao quiser limitar.
    private String email;
    @Column(name = "senha")
    private String senha;

    @OneToMany(cascade = CascadeType.ALL) //OneToMany - um usuario pode ter varios enderecos, e embaixo, telefones.
    @JoinColumn(name = "usuario_id", referencedColumnName = "id") //A entity Endereco é associada a ENTITY Usuario
    //pela usuario_id que referencia id do Usuario, é o relacionamento entre tabelas que ja sei.
    // Endereco.usuario_id → Usuario.id
    private List<Endereco> enderecos;

    @OneToMany(cascade = CascadeType.ALL) //cascade etc : Quando eu fizer algo com ESTE objeto, faça o MESMO com os
    // objetos relacionados. EX: tudo que eu fizer com usuario deve acontecer com seu telefone
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Telefone> telefones;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //metodo que usa generics
        return List.of(); //A interface UserDetails exige esse metodo
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    //Os métodos getUsername() e getPassword() vêm da interface UserDetails.

    //Você NÃO declarou usuario_id na classe Endereco nem Telefone, mas o JPA/Hibernate cria essa coluna automaticamente.
    //Em vez do OneToMany poderia passar OneToOne, se o usuario pudesse por exemplo ter somente um endereço




}
