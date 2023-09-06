package com.lembretes;

import com.lembretes.Controller.LembretesController;
import com.lembretes.Controller.PessoaController;
import com.lembretes.DTO.LembretesDTO;
import com.lembretes.Entity.Lembretes;
import com.lembretes.Entity.Pessoas;
import com.lembretes.Repository.LembretesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LembreteTest {

    @MockBean
    LembretesRepository repository;

    @Autowired
    LembretesController controller;

    @BeforeEach
    void injectData(){
        Optional<Lembretes> lembretes = Optional.of(new Lembretes((long) 1,"teste", new Pessoas((long) 1, "Gustavo")));


        List<Lembretes> lembretesList = new ArrayList<>();
        lembretesList.add(new Lembretes((long) 1, "teste", new Pessoas((long) 1, "Gustavo")));
        lembretesList.add(new Lembretes((long) 2, "teste", new Pessoas((long) 2, "Gustavo")));
        Mockito.when(repository.findAll()).thenReturn(lembretesList);
        Mockito.when(repository.findById(1L)).thenReturn(lembretes);

        Lembretes lembretesEntity = new Lembretes((long) 3,"teste", new Pessoas((long) 3, "Gustavo"));

        Mockito.when(repository.save(lembretesEntity)).thenReturn(lembretesEntity);


        LembretesDTO lembretesDTO = new LembretesDTO((long) 1,"teste", new Pessoas((long) 1, "Gustavo"));

        ResponseEntity<String> responseEntity = ResponseEntity.ok("Cadastrado com sucesso!");
        Mockito.when(controller.cadastrar(lembretesDTO)).thenReturn(responseEntity);

        ResponseEntity<String> responseEntity2 = ResponseEntity.ok("Editado com sucesso!");
        Mockito.when(controller.editar(lembretesDTO)).thenReturn(responseEntity2);

//        ResponseEntity<String> responseEntity3 = ResponseEntity.ok("Deletado com sucesso!");
//        Mockito.when(controller.deletar(1L)).thenReturn(responseEntity3);

    }


    @Test
    void TestLembretes(){
        var lembretescontroller = controller.getById(1L);
        Long id = lembretescontroller.getId();
        System.out.println("Lembretescontroller: " + lembretescontroller);
        System.out.println(id);
        Assertions.assertEquals(1L, id);
    }

    @Test
    void TestLembretesAll(){
        List<Lembretes> lembretes = new ArrayList<>();
        lembretes.add(new Lembretes((long) 1, "teste", new Pessoas((long) 1, "Gustavo")));
        lembretes.add(new Lembretes((long) 2, "teste", new Pessoas((long) 2, "Gustavo")));

        List<Lembretes> lembretescontroller = controller.getAll();

        for(int i=0; i<= lembretes.size()-1; i++) {
            Assertions.assertEquals(lembretes.get(i).getId(), lembretescontroller.get(i).getId());
            Assertions.assertEquals(lembretes.get(i).getDescricao(), lembretescontroller.get(i).getDescricao());
            Assertions.assertEquals(lembretes.get(i).getPessoa().getId(), lembretescontroller.get(i).getPessoa().getId());
        }
    }

    @Test
    void TestPostLembretes(){
        LembretesDTO lembretesDTO = new LembretesDTO((long) 3,"teste", new Pessoas((long) 3, "Gustavo"));
        ResponseEntity<String> response = controller.cadastrar(lembretesDTO);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), "Cadastrado com sucesso!");
    }

    @Test
    void TestPutLembretes(){
        LembretesDTO lembretesDTO = new LembretesDTO((long) 1,"teste", new Pessoas((long) 3, "Gustavo"));
        ResponseEntity<String> response = controller.editar(lembretesDTO);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), "Editado com sucesso!");
    }

    @Test
    void TestDeleteLembretes(){
        ResponseEntity<String> response = controller.deletar(1L);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), "Deletado com sucesso!");
    }





}
