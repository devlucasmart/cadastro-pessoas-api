package com.devlucasmart.cadastropessoas.service;

import com.devlucasmart.cadastropessoas.comum.exception.ValidacaoException;
import com.devlucasmart.cadastropessoas.dto.pessoa.PessoaRequest;
import com.devlucasmart.cadastropessoas.dto.pessoa.PessoaResponse;
import com.devlucasmart.cadastropessoas.mapper.EnderecoMapper;
import com.devlucasmart.cadastropessoas.mapper.PessoaMapper;
import com.devlucasmart.cadastropessoas.model.EnderecoModel;
import com.devlucasmart.cadastropessoas.model.PessoaModel;
import com.devlucasmart.cadastropessoas.repository.EnderecoRespository;
import com.devlucasmart.cadastropessoas.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository repository;
    private final EnderecoRespository enderecoRespository;
    private final PessoaMapper mapper;
    private final EnderecoMapper enderecoMapper;

    public List<PessoaResponse> findAll() {
        var pessoas = repository.findAll();
        return mapper.toListResponse(pessoas);
    }

    public PessoaResponse findById(Integer id) {
        var pessoa = getById(id);
        return mapper.toResponse(pessoa);
    }

    public PessoaResponse save(PessoaRequest request) {

        var pessoa = mapper.toDomain(request);
        repository.save(pessoa);
        return mapper.toResponse(pessoa);
    }

    public PessoaResponse update(Integer id, PessoaRequest request) {
        var pessoaExistente = getById(id);
        var pessoa = mapper.toDomain(request);
        pessoa.setId(pessoaExistente.getId());
        return mapper.toResponse(repository.save(pessoa));
    }

    public void delete(Integer id) {
        var pessoa = getById(id);
        repository.deleteById(pessoa.getId());
    }

    private PessoaModel getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ValidacaoException("Book não Encontrado!!"));
    }

    private void validaEndereco(Integer enderecoId){
        enderecoRespository.findById(enderecoId).orElseThrow(() -> new ValidacaoException("Endereço Não encontrada!!"));
    }
}
