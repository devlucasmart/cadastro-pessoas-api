package com.devlucasmart.cadastropessoas.mapper;

import com.devlucasmart.cadastropessoas.dto.pessoa.PessoaRequest;
import com.devlucasmart.cadastropessoas.dto.pessoa.PessoaResponse;
import com.devlucasmart.cadastropessoas.model.PessoaModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PessoaMapper {
    PessoaModel toDomain(PessoaRequest request);

    @Mapping(target = "endereco.pessoa", ignore = true)
    PessoaResponse toResponse(PessoaModel pessoa);

    List<PessoaResponse> toListResponse(List<PessoaModel> pessoa);
}
