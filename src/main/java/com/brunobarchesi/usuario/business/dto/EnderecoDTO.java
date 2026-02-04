package com.brunobarchesi.usuario.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EnderecoDTO {

    private String cep;
    private String numero;
    private String cidade;
    private String estado;
    private String complemento;
    private String rua;

}
