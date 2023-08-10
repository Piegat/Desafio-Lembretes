package com.lembretes.Repository;

import com.lembretes.Entity.Lembretes;
import com.lembretes.Entity.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LembretesRepository  extends JpaRepository<Lembretes, Long> {

    @Query("from Lembretes where pessoa.nome Like :nome")
    public List<Lembretes> findByNome(@Param("nome") final String nome);

}
