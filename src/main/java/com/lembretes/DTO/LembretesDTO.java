package com.lembretes.DTO;

import com.lembretes.Entity.Pessoas;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

public class LembretesDTO {

    private Long id;

    private String descricao;

    private Pessoas pessoa;


    public LembretesDTO(){}

    public LembretesDTO(Long id, String descricao, Pessoas pessoa) {
        this.id = id;
        this.descricao = descricao;
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pessoas getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoas pessoa) {
        this.pessoa = pessoa;
    }
}
