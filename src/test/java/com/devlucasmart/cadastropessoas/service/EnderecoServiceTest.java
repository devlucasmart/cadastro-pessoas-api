package com.devlucasmart.cadastropessoas.service;

import com.devlucasmart.cadastropessoas.comum.exception.ValidacaoException;
import com.devlucasmart.cadastropessoas.dto.pessoa.PessoaResponse;
import com.devlucasmart.cadastropessoas.helper.EnderecoHelper;
import com.devlucasmart.cadastropessoas.mapper.EnderecoMapper;
import com.devlucasmart.cadastropessoas.repository.EnderecoRespository;
import com.devlucasmart.cadastropessoas.repository.PessoaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.devlucasmart.cadastropessoas.helper.EnderecoHelper.umEndereco;
import static com.devlucasmart.cadastropessoas.helper.EnderecoHelper.umEnderecoNovo;
import static com.devlucasmart.cadastropessoas.helper.EnderecoHelper.umEnderecoRequest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class EnderecoServiceTest {
    @MockBean
    private EnderecoMapper mapper;
    @MockBean
    private EnderecoRespository repository;
    @MockBean
    private PessoaRepository pessoaRepository;
    @Autowired
    private EnderecoService service;

    @Test
    public void findAll_deveRetornarTodosEnderecos() {
        var listEnderecos = EnderecoHelper.umaListaEndereco();

        doReturn(listEnderecos).when(repository).findAll();
        service.findAll();

        verify(repository).findAll();
        verify(mapper).toListResponse(listEnderecos);
    }

    @Test
    public void findById_deveRetornarEnderecoPeloId() {
        var endereco = umEndereco();
        var response = new PessoaResponse();
        doReturn(Optional.of(endereco)).when(repository).findById(1);

        BeanUtils.copyProperties(endereco, response);
        service.findById(1);

        verify(repository).findById(1);
        verify(mapper).toResponse(endereco);
    }

    @Test
    public void findById_deveRetornarExceptionEnderecoNaoExistente() {
        assertThatThrownBy(() -> service.findById(4))
                .isInstanceOf(ValidacaoException.class)
                .hasMessage("Endereco não Encontrado!!");

        verify(repository).findById(4);
        verify(mapper, never()).toResponse(any());
    }

    @Test
    public void save_deveSalvarEndereco() {
        var request = umEnderecoRequest();
        var enderecoNovo = umEnderecoNovo(request);

        doReturn(Optional.of(enderecoNovo.getPessoa())).when(pessoaRepository).findById(1);
        doReturn(enderecoNovo).when(mapper).toDomain(request);
        doReturn(enderecoNovo).when(repository).save(enderecoNovo);

        service.save(request);

        verify(repository).save(enderecoNovo);
        verify(mapper).toDomain(request);
        verify(mapper).toResponse(enderecoNovo);
    }

    @Test
    public void save_deveRetornarExceptionQuandoPessoaNaoExistente() {
        doReturn(Optional.empty()).when(pessoaRepository).findById(1);

        assertThatThrownBy(() -> service.save(umEnderecoRequest()))
                .isInstanceOf(ValidacaoException.class)
                .hasMessage("Pessoa Não Encontrada!!");

        verify(pessoaRepository).findById(1);
        verify(repository, never()).findById(1);
        verify(repository, never()).save(any());
        verify(mapper, never()).toDomain(any());
        verify(mapper, never()).toResponse(any());
    }

    @Test
    public void update_deveAtualizarEndereco() {
        var request = umEnderecoRequest();
        var enderecoExistente = umEndereco();
        var enderecoAtualizado = umEnderecoNovo(request);

        doReturn(Optional.of(enderecoExistente)).when(repository).findById(1);
        doReturn(enderecoAtualizado).when(mapper).toDomain(request);
        doReturn(enderecoAtualizado).when(repository).save(enderecoAtualizado);

        service.update(1, request);

        verify(repository).save(enderecoAtualizado);
        verify(mapper).toDomain(request);
        verify(mapper).toResponse(enderecoAtualizado);
    }

    @Test
    public void update_deveRetornarExceptionQuandoEnderecoNaoExistente() {
        doReturn(Optional.empty()).when(repository).findById(1);

        assertThatThrownBy(() -> service.update(1, umEnderecoRequest()))
                .isInstanceOf(ValidacaoException.class)
                .hasMessage("Endereco não Encontrado!!");

        verify(repository).findById(1);
        verify(repository, never()).save(any());
        verify(mapper, never()).toDomain(any());
        verify(mapper, never()).toResponse(any());
    }

    @Test
    public void delete_deveDeletarEndereco() {
        doReturn(Optional.of(umEndereco())).when(repository).findById(1);

        service.delete(1);

        verify(repository).deleteById(1);
        verify(repository).findById(1);
    }
}
