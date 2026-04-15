package com.brunobarchesi.usuario.business.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoDTO {

    private String rua;
    private Long numero;
    private String complemento;
    private String cidade;
    private String cep;
    private String estado;

}
