package com.brunobarchesi.usuario.infrastucture.repository;

import com.brunobarchesi.usuario.infrastucture.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> { //Aqui digo que é do tipo ENTITY usuario
    //E QUE O ID É LONG

    boolean existsByEmail(String email); //Metodo que verifica se no banco de dados ja tem algum dado
    //retorna verdadeiro ou falso.

    Optional<Usuario> findByEmail(String email); // Optional<Usuario> indica que o metodo pode retornar um Usuario ou nenhum valor.
// Se existir email no banco, retorna o Usuario; se não, retorna Optional.empty(), se eu nao passasse optional teria que tratar
// erro caso o email nao existisse.

    @Transactional //Ajuda a nao causar nenhum erro na hora de deletar
    void deleteByEmail(String email);



}
