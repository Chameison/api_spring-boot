package com.curso.remedios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.curso.remedios.classes.DadosAtualizarRemedio;
import com.curso.remedios.classes.DadosCadastrosRemedio;
import com.curso.remedios.classes.DadosDetalhadoRemedio;
import com.curso.remedios.classes.DadosListarRemedio;
import com.curso.remedios.classes.Remedio;
import com.curso.remedios.repositories.RemedioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/remedios")
public class RemedioController {
    // Fazendo injecao de dependencia
    @Autowired //
    private RemedioRepository repository;

    @PostMapping
    @Transactional // quando algum erro acontece, ele faz com que a transacao seja revertida, caso
                   // necessario
    public ResponseEntity<DadosDetalhadoRemedio> cadastrar(@RequestBody @Valid DadosCadastrosRemedio dados,
            UriComponentsBuilder uriBuilder) {
        var remedio = new Remedio(dados); // criamos uma varaivel que recebe um novo dado com os parametros do dto,
        repository.save(remedio); // salvando o remedio
        var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri(); // utiliuzando uma classe
                                                                                             // para criar a uri,
                                                                                             // chamados o parametro, ai
                                                                                             // busca o caminho
        return ResponseEntity.created(uri).body(new DadosDetalhadoRemedio(remedio));
    }

    @GetMapping
    public ResponseEntity<List<DadosListarRemedio>> listar() { // nao podemos expor as entidades
        var lista = repository.findAllByAtivoTrue().stream().map(DadosListarRemedio::new).toList();
        return ResponseEntity.ok(lista);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhadoRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
        var remedio = repository.getReferenceById(dados.id());
        remedio.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhadoRemedio(remedio));
    }

    @DeleteMapping("/{id}") // parametros dinamicos
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) { // Path mostra que é o parametro dianmico, ele mostra o
                                                                 // caminho
        repository.deleteById(id); // sem recuperacao,
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        var remedio = repository.getReferenceById(id);
        remedio.inativar();
        return ResponseEntity.noContent().build();

    }

    @PutMapping("ativar/{id}")
    @Transactional
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        var remedio = repository.getReferenceById(id);
        remedio.ativar();
        return ResponseEntity.noContent().build();

    }
}
