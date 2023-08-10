package com.lembretes.Service;

import com.lembretes.DTO.PessoasDTO;
import com.lembretes.Entity.Pessoas;
import com.lembretes.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;


    public PessoasDTO toPessoasDTO(Pessoas pessoas){
        PessoasDTO pessoasDTO = new PessoasDTO();

        pessoasDTO.setNome(pessoas.getNome());
        pessoasDTO.setId(pessoas.getId());

        return pessoasDTO;
    }

    public Pessoas toPessoas(PessoasDTO pessoasDTO){
        Pessoas pessoas = new Pessoas();

        pessoas.setNome(pessoasDTO.getNome());
        pessoas.setId(pessoasDTO.getId());

        return  pessoas;
    }

    public List<PessoasDTO> findAll(){

        List<PessoasDTO> pessoasDTO = pessoaRepository.findAll().stream().map(this::toPessoasDTO).toList();

//  Solução 2        List<Pessoas> all = this.pessoaRepository.findAll();
//        List<PessoasDTO> allDTO = new ArrayList<>();
//
//        for (Pessoas pessoas: all
//             ) {
//             allDTO.add(toPessoasDTO(pessoas));
//        }
//
        return  pessoasDTO;
    }


    public PessoasDTO findNome(String nome){
        Pessoas pessoa =  this.pessoaRepository.findByNome(nome);

        PessoasDTO pessoaDTO = toPessoasDTO(pessoa);

        return pessoaDTO;
    }

    public PessoasDTO findById(Long id){
        Assert.notNull(id, "Id nulo");

        Pessoas pessoas = this.pessoaRepository.findById(id).orElse(null);

        PessoasDTO pessoasDTO = toPessoasDTO(pessoas);

        return pessoasDTO;
    }


    public Pessoas cadastrar(final PessoasDTO pessoasDTO){
        Assert.notNull(pessoasDTO.getNome(), "Nome não informado");

        Pessoas pessoas = new Pessoas();

        pessoas = toPessoas(pessoasDTO);
        return this.pessoaRepository.save(pessoas);
    }

    public Pessoas editar (final Long id, PessoasDTO pessoasDTO){

        Assert.notNull(pessoasDTO.getId(), "Não informado o ID");
        Assert.notNull(pessoasDTO.getNome(), "Nome não informado");
        Assert.hasText(pessoasDTO.getNome(), "Não informado corretamente o nome");

        final Pessoas pessoaBanco = this.pessoaRepository.findById(id).orElse(null);
        Assert.notNull(pessoaBanco, "Pessoa inexistente!");
        Assert.isTrue(pessoaBanco.getId().equals(pessoasDTO.getId()), "Pessoa informada não é a mesma que a Pessoa a ser atualizada");

        Pessoas pessoas = toPessoas(pessoasDTO);

        return this.pessoaRepository.save(pessoas);
    }

    public  String deletar(final Long id){

        final Pessoas pessoaBanco = this.pessoaRepository.findById(id).orElse(null);
        Assert.notNull(pessoaBanco, "Pessoa inexistente!");
        this.pessoaRepository.delete(pessoaBanco);

        return "Deletado com sucesso!";
    }

}
