package com.brunobarchesi.usuario.infrastucture.repository;

import com.brunobarchesi.usuario.infrastucture.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> { //Aqui digo que é do tipo ENTITY telefoneO
    //E QUE O ID É LONG
}
