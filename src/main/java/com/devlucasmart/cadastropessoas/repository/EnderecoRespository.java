package com.devlucasmart.cadastropessoas.repository;

import com.devlucasmart.cadastropessoas.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRespository extends JpaRepository<EnderecoModel, Integer> {
}
