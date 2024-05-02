package com.devlucasmart.cadastropessoas.dto.pessoa;

import com.devlucasmart.cadastropessoas.model.EnderecoModel;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PessoaResponse {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private List<EnderecoModel> endereco;
}
