package com.lembretes.Service;

import com.lembretes.Entity.Lembretes;
import com.lembretes.Entity.Pessoas;
import com.lembretes.Repository.LembretesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class LembretesService {

    @Autowired
    private LembretesRepository lembretesRepository;

    public Lembretes cadastrar(final Lembretes lembretes){

        Assert.notNull(lembretes.getPessoa(), "Pessoa não informada");

        Assert.notNull(lembretes.getDescricao(), "Descrição não informada");
        Assert.hasText(lembretes.getDescricao(), "Descrição invalida");

        return this.lembretesRepository.save(lembretes);
    }

    public Lembretes editar(final Lembretes lembretes){

        Assert.notNull(lembretes.getId(), "Id não informado");
        Assert.notNull(lembretes.getDescricao(), "Descrição não informada");
        Assert.hasText(lembretes.getDescricao(), "Descrição invalida");
        Assert.notNull(lembretes.getPessoa(), "Pessoa não informado");

        return this.lembretesRepository.save(lembretes);
    }

    public String deletar(final Long id){

        final Lembretes lembretesBanco = this.lembretesRepository.findById(id).orElse(null);
        Assert.notNull(lembretesBanco, "Lembrete inexistente!");
        this.lembretesRepository.delete(lembretesBanco);

        return "Deletado com sucesso!";

    }
}
