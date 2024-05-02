package com.devlucasmart.cadastropessoas.dto.endereco;

import com.devlucasmart.cadastropessoas.model.PessoaModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoRequest {
    private String logradouro;
    private String cep;
    private Integer numero;
    private String cidade;
    private String estado;
    private Boolean enderecoPrincipal;
    private PessoaModel pessoa;
}
