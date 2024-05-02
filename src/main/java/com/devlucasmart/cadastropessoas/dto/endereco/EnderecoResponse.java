package com.devlucasmart.cadastropessoas.dto.endereco;

import com.devlucasmart.cadastropessoas.model.PessoaModel;
import lombok.Data;

@Data
public class EnderecoResponse {
    private Integer id;
    private String logradouro;
    private String cep;
    private Integer numero;
    private String cidade;
    private String estado;
    private PessoaModel pessoa;
}
