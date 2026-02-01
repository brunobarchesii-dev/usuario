package com.brunobarchesi.usuario.infrastucture.repository;

import com.bruno.aprendendo_spring.infrastructure.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> { //Aqui digo que é do tipo ENTITY ENDERECO
    //E QUE O ID É LONG
}
