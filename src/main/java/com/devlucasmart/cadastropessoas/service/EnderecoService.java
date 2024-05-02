package com.devlucasmart.cadastropessoas.service;

import com.devlucasmart.cadastropessoas.comum.exception.ValidacaoException;
import com.devlucasmart.cadastropessoas.dto.endereco.EnderecoRequest;
import com.devlucasmart.cadastropessoas.dto.endereco.EnderecoResponse;
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
public class EnderecoService {
    private final EnderecoRespository repository;
    private final PessoaRepository pessoaRepository;
    private final EnderecoMapper mapper;

    public List<EnderecoResponse> findAll() {
        var enderecos = repository.findAll();
        return mapper.toListResponse(enderecos);
    }

    public EnderecoResponse findById(Integer id) {
        var endereco = getById(id);
        return mapper.toResponse(endereco);
    }

    public EnderecoResponse save(EnderecoRequest request) {
        var pessoa = pessoaRepository.findById(request.getPessoa().getId()).orElseThrow(() -> new ValidacaoException("Pessoa Não encontrada"));
        var endereco = mapper.toDomain(request);
        endereco.setEnderecoPrincipal(request.getEnderecoPrincipal());
        endereco.setPessoa(pessoa);
        repository.save(endereco);
        return mapper.toResponse(endereco);
    }

    public EnderecoResponse update(Integer id, EnderecoRequest request) {
        var enderecoExistente = getById(id);
        var endereco = mapper.toDomain(request);
        endereco.setEnderecoPrincipal(request.getEnderecoPrincipal());
        endereco.setId(enderecoExistente.getId());
        return mapper.toResponse(repository.save(endereco));
    }

    public void delete(Integer id) {
        var endereco = getById(id);
        repository.deleteById(endereco.getId());
    }

    private EnderecoModel getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ValidacaoException("Endereco não Encontrado!!"));
    }
}
