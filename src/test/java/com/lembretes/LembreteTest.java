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
import org.springframework.util.Assert;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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





}
