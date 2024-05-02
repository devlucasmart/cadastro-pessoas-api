package com.devlucasmart.cadastropessoas.mapper;

import com.devlucasmart.cadastropessoas.dto.endereco.EnderecoRequest;
import com.devlucasmart.cadastropessoas.dto.endereco.EnderecoResponse;
import com.devlucasmart.cadastropessoas.model.EnderecoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    EnderecoModel toDomain(EnderecoRequest request);

    EnderecoRequest toRequest(EnderecoModel endereco);

    @Mapping(target = "pessoa.endereco", ignore = true)
    EnderecoResponse toResponse(EnderecoModel endereco);

    List<EnderecoResponse> toListResponse(List<EnderecoModel> endereco);
}
