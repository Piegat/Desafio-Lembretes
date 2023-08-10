package com.lembretes.Controller;

import com.lembretes.Entity.Lembretes;
import com.lembretes.Repository.LembretesRepository;
import com.lembretes.Service.LembretesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Signature;
import java.util.List;

@RestController
@RequestMapping("/lembretes")
public class LembretesController {


    @Autowired
    private LembretesRepository lembretesRepository;

    @Autowired
    private LembretesService lembretesService;

    @GetMapping("/all")
    private List<Lembretes> getAll(){
        return this.lembretesRepository.findAll();
    }

    @GetMapping
    private List<Lembretes> getByNome(@RequestParam("nome") final String nome){
            return this.lembretesRepository.findByNome(nome);
    }


    @PostMapping
    private ResponseEntity<String> cadastrar(@RequestBody Lembretes lembretes){
        try{
            this.lembretesService.cadastrar(lembretes);
            return ResponseEntity.ok().body("Cadastrado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    private ResponseEntity<String> editar(@RequestBody Lembretes lembretes){
        try{
            this.lembretesService.editar(lembretes);
            return ResponseEntity.ok().body("Editado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    private ResponseEntity<String> deletar(@RequestParam("id") final Long id){
        try{
            this.lembretesService.deletar(id);
            return ResponseEntity.ok().body("Deletado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
