package com.brunobarchesi.usuario.business;

import com.brunobarchesi.usuario.infrastucture.clients.ViaCepClient;
import com.brunobarchesi.usuario.infrastucture.clients.ViaCepDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient viaCepClient;

    public ViaCepDTO buscarCEP(String cep){
        String cepValido = procesarCEP(cep);
        return viaCepClient.buscaDadosEndereco(cepValido);
    }


    //Validar cep passado, pois n pode ter espacos, traços etc
    private String procesarCEP(String cep) {
        String cepFormatado = cep.replace(" ", "")//Se tiver espaco em branco retira e deixa vazio
                .replace("-", ""); //Se tiver traço tira e deixa vazio

        if (!cepFormatado.matches("[0-9]+") || !Objects.equals(cepFormatado.length(), 8)) {
            //Se o cep passsado nao corresponder a digitos de 0 a 9 mais de uma vez(+),
            //E se o objeto cep nao for igual a tamanho 8, é invalido
            throw new RuntimeException("CEP invalido, somente numeros sao aceitos e o tamanho sao de 8 digitos");
        }
            return cepFormatado;
        }




}
