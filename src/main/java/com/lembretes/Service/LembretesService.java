package com.lembretes.Service;

import com.lembretes.DTO.LembretesDTO;
import com.lembretes.DTO.PessoasDTO;
import com.lembretes.Entity.Lembretes;
import com.lembretes.Entity.Pessoas;
import com.lembretes.Repository.LembretesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class LembretesService {

    @Autowired
    private LembretesRepository lembretesRepository;


    public LembretesDTO toLembretesDTO(Lembretes lembretes){
        LembretesDTO lembretesDTO = new LembretesDTO();

        lembretesDTO.setId(lembretes.getId());
        lembretesDTO.setDescricao(lembretes.getDescricao());
        lembretesDTO.setPessoa(lembretes.getPessoa());

        return  lembretesDTO;
    }

    public Lembretes toLembretes(LembretesDTO lembretesDTO){
        Lembretes lembretes = new Lembretes();

        lembretes.setId(lembretesDTO.getId());
        lembretes.setDescricao(lembretesDTO.getDescricao());
        lembretes.setPessoa(lembretesDTO.getPessoa());

        return lembretes;
    }

    public List<LembretesDTO> findAll (){

        List<LembretesDTO> lembretesDTOS = lembretesRepository.findAll().stream().map(this::toLembretesDTO).toList();

        return lembretesDTOS;
    }

    public List<LembretesDTO> findNome(String nome){

        List<LembretesDTO> lembretesDTOS = lembretesRepository.findByNome(nome).stream().map(this::toLembretesDTO).toList();

        return lembretesDTOS;
    }

    public LembretesDTO findById(Long id){

        Assert.notNull(id, "Id é nulo");
        Lembretes lembretes =this.lembretesRepository.findById(id).orElse(null);

        LembretesDTO lembretesDTO = toLembretesDTO(lembretes);

        return lembretesDTO;
    }

    public Lembretes cadastrar(final LembretesDTO lembretesDTO ){

        Assert.notNull(lembretesDTO.getPessoa(), "Pessoa não informada");

        Assert.notNull(lembretesDTO.getDescricao(), "Descrição não informada");
        Assert.hasText(lembretesDTO.getDescricao(), "Descrição invalida");

        Lembretes lembretes = toLembretes(lembretesDTO);

        return this.lembretesRepository.save(lembretes);
    }

    public Lembretes editar(final LembretesDTO lembretesDTO){

        Assert.notNull(lembretesDTO.getId(), "Id não informado");
        Assert.notNull(lembretesDTO.getDescricao(), "Descrição não informada");
        Assert.hasText(lembretesDTO.getDescricao(), "Descrição invalida");
        Assert.notNull(lembretesDTO.getPessoa(), "Pessoa não informado");

        Lembretes lembretes = toLembretes(lembretesDTO);

        return this.lembretesRepository.save(lembretes);
    }

    public String deletar(final Long id){

        final Lembretes lembretesBanco = this.lembretesRepository.findById(id).orElse(null);
        Assert.notNull(lembretesBanco, "Lembrete inexistente!");
        this.lembretesRepository.delete(lembretesBanco);

        return "Deletado com sucesso!";

    }
}
