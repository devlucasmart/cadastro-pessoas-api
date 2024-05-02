package com.devlucasmart.cadastropessoas.dto.pessoa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaRequest {
    private String nome;
    private LocalDate dataNascimento;
}
