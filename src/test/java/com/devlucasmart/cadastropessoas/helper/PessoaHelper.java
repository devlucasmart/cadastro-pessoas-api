package com.devlucasmart.cadastropessoas.helper;

import com.devlucasmart.cadastropessoas.dto.pessoa.PessoaRequest;
import com.devlucasmart.cadastropessoas.model.EnderecoModel;
import com.devlucasmart.cadastropessoas.model.PessoaModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.devlucasmart.cadastropessoas.helper.EnderecoHelper.umEndereco;

public class PessoaHelper {
    public static PessoaRequest umaPessoaRequest() {
        return PessoaRequest.builder()
                .nome("Pedro Oliveira")
                .dataNascimento(LocalDate.of(2023, 1, 1))
                .build();
    }

    public static PessoaModel umaPessoaNova(PessoaRequest request) {
        return PessoaModel.builder()
                .nome(request.getNome())
                .dataNascimento(request.getDataNascimento())
                .build();
    }

    public static PessoaModel umaPessoa() {
        return PessoaModel.builder()
                .id(1)
                .nome("Lucas Martins")
                .dataNascimento(LocalDate.of(2023, 1, 1))
                .endereco(umaListaEndereco())
                .build();
    }

    public static PessoaModel duasPessoa() {
        return PessoaModel.builder()
                .id(2)
                .nome("Lucas Martins")
                .dataNascimento(LocalDate.of(2023, 1, 1))
                .endereco(umaListaEndereco())
                .build();
    }

    public static PessoaModel tresPessoa() {
        return PessoaModel.builder()
                .id(3)
                .nome("Lucas Martins")
                .dataNascimento(LocalDate.of(2023, 1, 1))
                .endereco(umaListaEndereco())
                .build();
    }

    public static List<PessoaModel> umaListaPessoa() {
        var listPessoa = new ArrayList<PessoaModel>();
        listPessoa.add(umaPessoa());
        listPessoa.add(duasPessoa());
        listPessoa.add(tresPessoa());
        return listPessoa;
    }

    public static List<EnderecoModel> umaListaEndereco() {
        var listEndereco = new ArrayList<EnderecoModel>();
        listEndereco.add(umEndereco());
        return listEndereco;
    }
}
