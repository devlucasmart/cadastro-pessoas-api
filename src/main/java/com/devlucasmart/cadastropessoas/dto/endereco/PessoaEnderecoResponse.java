package com.devlucasmart.cadastropessoas.dto.endereco;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaEnderecoResponse {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
}
