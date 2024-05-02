package com.devlucasmart.cadastropessoas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_endereco")
public class EnderecoModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String logradouro;
    private String cep;
    private Integer numero;
    private String cidade;
    private String estado;
    private Boolean enderecoPrincipal;
    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.DETACH)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @JsonBackReference
    private PessoaModel pessoa;
}
