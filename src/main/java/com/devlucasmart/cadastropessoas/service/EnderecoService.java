package com.devlucasmart.cadastropessoas.service;

import com.devlucasmart.cadastropessoas.comum.exception.ValidacaoException;
import com.devlucasmart.cadastropessoas.dto.endereco.EnderecoRequest;
import com.devlucasmart.cadastropessoas.dto.endereco.EnderecoResponse;
import com.devlucasmart.cadastropessoas.mapper.EnderecoMapper;
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
        var endereco = validaEndereco(id);
        return mapper.toResponse(endereco);
    }

    public EnderecoResponse save(EnderecoRequest request) {
        var pessoa = validaPessoa(request.getPessoa().getId());
        var endereco = mapper.toDomain(request);
        endereco.setEnderecoPrincipal(request.getEnderecoPrincipal());
        endereco.setPessoa(pessoa);
        repository.save(endereco);
        return mapper.toResponse(endereco);
    }

    public EnderecoResponse update(Integer id, EnderecoRequest request) {
        validaPessoa(request.getPessoa().getId());
        var enderecoExistente = validaEndereco(id);
        var endereco = mapper.toDomain(request);
        endereco.setEnderecoPrincipal(request.getEnderecoPrincipal());
        endereco.setId(enderecoExistente.getId());
        return mapper.toResponse(repository.save(endereco));
    }

    public void delete(Integer id) {
        var endereco = validaEndereco(id);
        repository.deleteById(endereco.getId());
    }

    private PessoaModel validaPessoa(Integer id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Pessoa Não Encontrada!!"));
    }

    private EnderecoModel validaEndereco(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ValidacaoException("Endereco não Encontrado!!"));
    }
}
