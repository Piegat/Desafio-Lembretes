package com.lembretes;

import com.lembretes.Controller.LembretesController;
import com.lembretes.Controller.PessoaController;
import com.lembretes.DTO.LembretesDTO;
import com.lembretes.DTO.PessoasDTO;
import com.lembretes.Entity.Lembretes;
import com.lembretes.Entity.Pessoas;
import com.lembretes.Repository.LembretesRepository;
import com.lembretes.Repository.PessoaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PessoaTest {

    @MockBean
    PessoaRepository repository;

    @Autowired
    PessoaController controller;

    @BeforeEach
    void injectData(){
        Optional<Pessoas> pessoas = Optional.of(new Pessoas((long) 1, "Gustavo"));

        List<Pessoas> pessoasList = new ArrayList<>();
        pessoasList.add(new Pessoas((long) 1, "Gustavo"));
        pessoasList.add(new Pessoas((long) 2, "Gustavo"));

        Pessoas pessoas1 = new Pessoas((long) 1, "Gustavo");

        Mockito.when(repository.findAll()).thenReturn(pessoasList);
        Mockito.when(repository.findById(1L)).thenReturn(pessoas);
        Mockito.when(repository.findByNome("Gustavo")).thenReturn(pessoas1);


        Pessoas pessoasEntity = new Pessoas((long) 3, "Gustavo");

        Mockito.when(repository.save(pessoasEntity)).thenReturn(pessoasEntity);

        PessoasDTO pessoasDTO =  new PessoasDTO((long) 1, "Gustavo");

        ResponseEntity<String> responseEntity = ResponseEntity.ok("Pessoa cadastrada com sucesso");
        Mockito.when(controller.cadastrar(pessoasDTO)).thenReturn(responseEntity);

        ResponseEntity<String> responseEntity2 = ResponseEntity.ok("Editado com sucesso!");
        Mockito.when(controller.editar(1L, pessoasDTO)).thenReturn(responseEntity2);


    }


    @Test
    void TestPessoasByID(){
        var pessoacontroller = controller.getById(1L);
        Long id = pessoacontroller.getId();
        System.out.println("Pessoas controller: " + pessoacontroller);
        System.out.println(id);
        Assertions.assertEquals(1L, id);
    }

    @Test
    void TestPessoasByNome(){
        var pessoacontroller = controller.getByNome("Gustavo");
        String nome = pessoacontroller.getNome();
        System.out.println("Pessoas controller Nome: " + pessoacontroller);
        System.out.println(nome);
        Assertions.assertEquals("Gustavo", nome);
    }



    @Test
    void TestPessoasAll(){
        List<Pessoas> pessoas = new ArrayList<>();
        pessoas.add(new Pessoas((long) 1, "Gustavo"));
        pessoas.add(new Pessoas((long) 2, "Gustavo"));

        List<PessoasDTO> pessoasDTOController = controller.getAll();

        for(int i=0; i<= pessoas.size()-1; i++) {
            Assertions.assertEquals(pessoas.get(i).getId(), pessoasDTOController.get(i).getId());
            Assertions.assertEquals(pessoas.get(i).getNome(), pessoasDTOController.get(i).getNome());
        }
    }

    @Test
    void TestPostPessoa(){
        PessoasDTO pessoasDTO = new PessoasDTO((long) 3, "Gustavo");
        ResponseEntity<String> response = controller.cadastrar(pessoasDTO);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), "Pessoa cadastrada com sucesso");
    }

    @Test
    void TestPutPessoas(){
        PessoasDTO pessoasDTO =  new PessoasDTO((long) 1, "Gustavo");
        ResponseEntity<String> response = controller.editar(1L, pessoasDTO);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), "Editado com sucesso!");
    }

    @Test
    void TestDeletePessoas(){
        ResponseEntity<String> response = controller.deletar(1L);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), "Deletado com sucesso");
    }






}
