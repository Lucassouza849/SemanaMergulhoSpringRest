package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.repository.ClientRepository;
import com.algaworks.algalog.domain.service.CatalogoClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {


    private ClientRepository clientRepository;
    private CatalogoClientService catalogoClientService;


    @GetMapping
    public List<Client> listar(){
        return clientRepository.findAll();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> buscar(@PathVariable Long clientId){
        return clientRepository.findById(clientId)
//                .map(client -> ResponseEntity.ok(client))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

        //código menos enxuto, mesmo funcinamento
//        Optional<Client> client = clientRepository.findById(clientId);
//        if (client.isPresent()){
//            return ResponseEntity.ok(client.get());
//        }
//        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client adicionar(@Valid @RequestBody Client client){
        return catalogoClientService.salvar(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> atualizar(
            @PathVariable Long clientId,
            @Valid @RequestBody Client client){
            if (!clientRepository.existsById(clientId)){
                return ResponseEntity.notFound().build();
            }
            client.setId(clientId);
            client = catalogoClientService.salvar(client);
            return ResponseEntity.ok(client);
    }


    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> remover(@PathVariable Long clientId){
        if (!clientRepository.existsById(clientId)){
            return ResponseEntity.notFound().build();
        }
        catalogoClientService.excluir(clientId);
        return ResponseEntity.noContent().build();
    }


}

