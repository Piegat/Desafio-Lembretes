package com.lembretes.Controller;

import com.lembretes.Entity.Pessoas;
import com.lembretes.Repository.PessoaRepository;
import com.lembretes.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;


    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/all")
    private List<Pessoas> findByAll(){
        return this.pessoaRepository.findAll();
    }

    @GetMapping("/nome")
    private List<Pessoas> findByNome(@RequestParam("nome") String nome){
        return this.pessoaRepository.findByNome(nome);
    }


    @PostMapping
    private ResponseEntity<String> cadastrar(@RequestBody Pessoas pessoas){
        try {
            this.pessoaService.cadastrar(pessoas);
            return ResponseEntity.ok("Pessoa cadastrada com sucesso");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    private ResponseEntity<String> editar(@RequestParam("id") final Long id, @RequestBody final Pessoas pessoa){
        try {
            this.pessoaService.editar(id, pessoa);

            return ResponseEntity.ok().body("Editado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    private ResponseEntity<String> deletar(@RequestParam("id") final Long id){
        try{
            this.pessoaService.deletar(id);

            return ResponseEntity.ok().body("Deletado com sucesso");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
