package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CatalogoClientService {
    private ClientRepository clientRepository;

    public Client buscar(Long clientId){
     return clientRepository.findById(clientId)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

    @Transactional
    public Client salvar(Client client){
        boolean emailuso = clientRepository.findByEmail(client.getEmail())
                .stream()
                .anyMatch(clientExistente -> !clientExistente.equals(client));
        if (emailuso){
            throw new NegocioException("Já existe um cliente cadastrado com esse e-mail");
        }

        return clientRepository.save(client);
    }

    @Transactional
    public void excluir(Long clientId){
        clientRepository.deleteById(clientId);
    }
}
