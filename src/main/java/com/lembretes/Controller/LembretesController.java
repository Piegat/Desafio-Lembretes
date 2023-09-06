package com.lembretes.Controller;

import com.lembretes.DTO.LembretesDTO;
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
    private LembretesService lembretesService = new LembretesService();

    @GetMapping("/all")
    public List<Lembretes> getAll(){
        return this.lembretesRepository.findAll();
    }

    @GetMapping("/id")
    public LembretesDTO getById(@RequestParam("id") final Long id){
        return this.lembretesService.findById(id);
    }

    @GetMapping("/nome")
    private List<LembretesDTO> getByNome(@RequestParam("nome") final String nome){
            return this.lembretesService.findNome(nome);
    }


    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody LembretesDTO lembretesDTO){
        try{
            this.lembretesService.cadastrar(lembretesDTO);
            return ResponseEntity.ok().body("Cadastrado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> editar(@RequestBody LembretesDTO lembretesDTO){
        try{
            this.lembretesService.editar(lembretesDTO);
            return ResponseEntity.ok().body("Editado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deletar(@RequestParam("id") final Long id){
        try{
            this.lembretesService.deletar(id);
            return ResponseEntity.ok().body("Deletado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
