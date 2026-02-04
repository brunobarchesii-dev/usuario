package com.brunobarchesi.usuario.business.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UsuarioDTO {

    private String nome;
    private String senha;
    private String email;
    private List<EnderecoDTO> enderecos;
    private List<TelefoneDTO> telefones;
 //Aqui nao expus o ID do usuario que tem na entity, somente o necessario.


}
