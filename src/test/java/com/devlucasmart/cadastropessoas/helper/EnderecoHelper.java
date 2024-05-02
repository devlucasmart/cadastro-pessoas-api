package com.devlucasmart.cadastropessoas.helper;

import com.devlucasmart.cadastropessoas.dto.endereco.EnderecoRequest;
import com.devlucasmart.cadastropessoas.model.EnderecoModel;

import java.util.ArrayList;
import java.util.List;

import static com.devlucasmart.cadastropessoas.helper.PessoaHelper.umaPessoa;

public class EnderecoHelper {
    public static EnderecoRequest umEnderecoRequest() {
        return EnderecoRequest.builder()
                .logradouro("Casa")
                .cep("73814500")
                .enderecoPrincipal(true)
                .cidade("Formosa")
                .estado("GO")
                .numero(23)
                .pessoa(umaPessoa())
                .build();
    }

    public static EnderecoModel umEnderecoNovo(EnderecoRequest request) {
        return EnderecoModel.builder()
                .logradouro(request.getLogradouro())
                .cep(request.getCep())
                .enderecoPrincipal(request.getEnderecoPrincipal())
                .cidade(request.getCidade())
                .estado(request.getEstado())
                .numero(request.getNumero())
                .pessoa(request.getPessoa())
                .build();
    }

    public static List<EnderecoModel> umaListaEndereco() {
        var listEndereco = new ArrayList<EnderecoModel>();
        listEndereco.add(umEndereco());
        return listEndereco;
    }

    public static EnderecoModel umEndereco() {
        return EnderecoModel.builder()
                .id(1)
                .logradouro("Casa")
                .cep("73814500")
                .enderecoPrincipal(true)
                .cidade("Formosa")
                .estado("GO")
                .numero(23)
                .build();
    }
}
