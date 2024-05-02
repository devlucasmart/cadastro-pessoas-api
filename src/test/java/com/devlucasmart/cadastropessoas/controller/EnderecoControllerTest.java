package com.devlucasmart.cadastropessoas.controller;

import com.devlucasmart.cadastropessoas.helper.TestsHelper;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.devlucasmart.cadastropessoas.helper.EnderecoHelper.umEnderecoRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EnderecoControllerTest {

    public static final String API_URI = "/api/endereco";

    @Autowired
    private MockMvc mvc;

    @Test
    public void findAll_deveRetornarOk() throws Exception {
        mvc.perform(get(API_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void findById_deveRetornarOk() {
        mvc.perform(get(API_URI + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void save_deveRetornarOk() {
        mvc.perform(post(API_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestsHelper.convertObjectToJsonBytes(umEnderecoRequest())))
                .andExpect(status().isCreated());
    }

    @Test
    @SneakyThrows
    public void update_deveRetornarOk() {
        mvc.perform(put(API_URI + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestsHelper.convertObjectToJsonBytes(umEnderecoRequest())))
                .andExpect(status().isNoContent());
    }

    @Test
    @SneakyThrows
    public void delete_deveRetornarOk() {
        mvc.perform(delete(API_URI + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
