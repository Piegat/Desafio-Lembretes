package com.lembretes.Controller;

import com.lembretes.DTO.PessoasDTO;
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
    public List<PessoasDTO> getAll(){
        return this.pessoaService.findAll();
    }

    @GetMapping("/id")
    public PessoasDTO getById(@RequestParam("id") Long id){
        return this.pessoaService.findById(id);
    }

    @GetMapping("/nome")
    public PessoasDTO getByNome(@RequestParam("nome") String nome){
        return this.pessoaService.findNome(nome);
    }


    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody PessoasDTO pessoasDTO){
        try {
            this.pessoaService.cadastrar(pessoasDTO);
            return ResponseEntity.ok("Pessoa cadastrada com sucesso");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> editar(@RequestParam("id") final Long id, @RequestBody final PessoasDTO pessoasDTO){
        try {
            this.pessoaService.editar(id, pessoasDTO);

            return ResponseEntity.ok().body("Editado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deletar(@RequestParam("id") final Long id){
        try{
            this.pessoaService.deletar(id);

            return ResponseEntity.ok().body("Deletado com sucesso");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
