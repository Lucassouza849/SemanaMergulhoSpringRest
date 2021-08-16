package com.algaworks.algalog.domain.repository;

import com.algaworks.algalog.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


    List<Client> findByNome(String nome);
    List<Client> findByNomeContaining(String  nome);
    Optional<Client> findByEmail(String email);

}
