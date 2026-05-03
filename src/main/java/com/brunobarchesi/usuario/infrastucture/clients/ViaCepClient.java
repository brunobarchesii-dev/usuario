package com.brunobarchesi.usuario.infrastucture.clients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "via-cep", url = "${viacepurl}")
//name é o nome que quero dar a minha api, e url é a url dela, que nesse caso coloquei para apontar pra uma variavel em /.properties
public interface ViaCepClient {

    @GetMapping("/ws/{cep}/json/")
    ViaCepDTO buscaDadosEndereco(@PathVariable("cep") String cep);

}
