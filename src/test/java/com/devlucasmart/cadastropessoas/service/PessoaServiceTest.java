package com.devlucasmart.cadastropessoas.service;

import com.devlucasmart.cadastropessoas.comum.exception.ValidacaoException;
import com.devlucasmart.cadastropessoas.dto.pessoa.PessoaResponse;
import com.devlucasmart.cadastropessoas.helper.PessoaHelper;
import com.devlucasmart.cadastropessoas.mapper.PessoaMapper;
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
import static com.devlucasmart.cadastropessoas.helper.PessoaHelper.umaPessoa;
import static com.devlucasmart.cadastropessoas.helper.PessoaHelper.umaPessoaNova;
import static com.devlucasmart.cadastropessoas.helper.PessoaHelper.umaPessoaRequest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class PessoaServiceTest {
    @MockBean
    private PessoaMapper mapper;
    @MockBean
    private PessoaRepository repository;
    @MockBean
    private EnderecoRespository enderecoRepository;
    @Autowired
    private PessoaService service;

    @Test
    public void findAll_deveRetornarTodasPessoas() {
        var listPessoa = PessoaHelper.umaListaPessoa();

        doReturn(listPessoa).when(repository).findAll();
        service.findAll();

        verify(repository).findAll();
        verify(mapper).toListResponse(listPessoa);
    }

    @Test
    public void findById_deveRetornarPessoaPeloId() {
        var pessoa = umaPessoa();
        var response = new PessoaResponse();
        doReturn(Optional.of(pessoa)).when(repository).findById(1);

        BeanUtils.copyProperties(pessoa, response);
        service.findById(1);

        verify(repository).findById(1);
        verify(mapper).toResponse(pessoa);
    }

    @Test
    public void findById_deveRetornarExceptionPessoaNaoExistente() {
        assertThatThrownBy(() -> service.findById(4))
                .isInstanceOf(ValidacaoException.class)
                .hasMessage("Pessoa não Encontrada!!");

        verify(repository).findById(4);
        verify(mapper, never()).toResponse(any());
    }

    @Test
    public void save_deveSalvarPessoa() {
        var request = umaPessoaRequest();
        var pessoaNova = umaPessoaNova(request);

        doReturn(Optional.of(umEndereco())).when(enderecoRepository).findById(1);
        doReturn(pessoaNova).when(mapper).toDomain(request);
        doReturn(pessoaNova).when(repository).save(pessoaNova);

        service.save(request);

        verify(repository).save(pessoaNova);
        verify(mapper).toDomain(request);
        verify(mapper).toResponse(pessoaNova);
    }

    @Test
    public void update_deveAtualizarPessoa() {
        var request = umaPessoaRequest();
        var pessoaExistente = umaPessoa();
        var pessoaAtualizado = umaPessoaNova(request);

        doReturn(Optional.of(pessoaExistente)).when(repository).findById(1);
        doReturn(pessoaAtualizado).when(mapper).toDomain(request);
        doReturn(pessoaAtualizado).when(repository).save(pessoaAtualizado);

        service.update(1, request);

        verify(repository).save(pessoaAtualizado);
        verify(mapper).toDomain(request);
        verify(mapper).toResponse(pessoaAtualizado);
    }

    @Test
    public void update_deveRetornarExceptionQuandoPessoaNaoExistente() {
        doReturn(Optional.empty()).when(repository).findById(1);

        assertThatThrownBy(() -> service.update(1, umaPessoaRequest()))
                .isInstanceOf(ValidacaoException.class)
                .hasMessage("Pessoa não Encontrada!!");

        verify(repository).findById(1);
        verify(repository, never()).save(any());
        verify(mapper, never()).toDomain(any());
        verify(mapper, never()).toResponse(any());
    }

    @Test
    public void delete_deveDeletarEndereco() {
        doReturn(Optional.of(umaPessoa())).when(repository).findById(1);

        service.delete(1);

        verify(repository).deleteById(1);
        verify(repository).findById(1);
    }
}
