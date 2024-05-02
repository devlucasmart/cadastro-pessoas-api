package com.devlucasmart.cadastropessoas.repository;

import com.devlucasmart.cadastropessoas.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Integer> {
}
