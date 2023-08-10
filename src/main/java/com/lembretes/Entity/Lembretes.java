package com.lembretes.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "lembretes", schema = "lembretes")
public class Lembretes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @JoinColumn(name = "id_pessoa", nullable = false)
    @ManyToOne
    private Pessoas pessoa;




}
