package com.lembretes.Repository;

import com.lembretes.Entity.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository  extends JpaRepository<Pessoas, Long> {


    @Query("from Pessoas where nome Like :nome")
    public List<Pessoas> findByNome(@Param("nome")final String nome);

}
