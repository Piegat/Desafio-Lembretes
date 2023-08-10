package com.lembretes.Service;

import com.lembretes.Entity.Pessoas;
import com.lembretes.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoas cadastrar(final Pessoas pessoa){
        Assert.notNull(pessoa.getNome(), "Nome não informado");
        return this.pessoaRepository.save(pessoa);
    }

    public Pessoas editar (final Long id, Pessoas pessoa){

        Assert.notNull(pessoa.getId(), "Não informado o ID");
        Assert.notNull(pessoa.getNome(), "Nome não informado");
        Assert.hasText(pessoa.getNome(), "Não informado corretamente o nome");

        final Pessoas pessoaBanco = this.pessoaRepository.findById(id).orElse(null);
        Assert.notNull(pessoaBanco, "Pessoa inexistente!");
        Assert.isTrue(pessoaBanco.getId().equals(pessoa.getId()), "Pessoa informada não é a mesma que a Pessoa a ser atualizada");

        return this.pessoaRepository.save(pessoa);
    }

    public  String deletar(final Long id){

        final Pessoas pessoaBanco = this.pessoaRepository.findById(id).orElse(null);
        Assert.notNull(pessoaBanco, "Pessoa inexistente!");
        this.pessoaRepository.delete(pessoaBanco);

        return "Deletado com sucesso!";
    }

}
