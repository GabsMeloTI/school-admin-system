package com.system.school.domain;

import com.system.school.dto.endereco.AlterarEnderecoDto;
import com.system.school.dto.endereco.CadastroEnderecoDto;
import com.system.school.dto.endereco.ListagemEnderecoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "tb_endereco")
public class Endereco {
    @Id
    @Column(name = "cd_endereco")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(name = "nm_rua", nullable = false, length = 150)
    private String rua;

    @Column(name = "nr_endereco", nullable = false, length = 10)
    private Integer numero;

    @Column(name = "ds_complemento", nullable = false, length = 100)
    private String complemento;

    @Column(name = "nm_bairro", nullable = false, length = 150)
    private String bairro;

    @Column(name = "nm_estado", nullable = false, length = 25)
    private String cidade;

    @Column(name = "sg_estado")
    private Estado estado;

    @Column(name = "nr_cep", nullable = false, length = 8)
    private String cep;

    @OneToMany(mappedBy = "endereco", cascade = CascadeType.ALL)
    private List<Aluno> alunos;

    public Endereco(CadastroEnderecoDto dto) {
        this.rua = dto.rua();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
        this.bairro = dto.bairro();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
        this.cep = dto.cep();
    }

    public Endereco(ListagemEnderecoDto dto) {
        this.rua = dto.rua();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
        this.bairro = dto.bairro();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
        this.cep = dto.cep();
    }


    public void alterar(AlterarEnderecoDto dto) {
        if(rua != null) {
            this.rua = dto.rua();
        }
        if(numero != null) {
            this.numero = dto.numero();
        }
        if(complemento != null) {
            this.complemento = dto.complemento();
        }
        if(bairro != null) {
            this.bairro = dto.bairro();
        }
        if(cidade != null) {
            this.cidade = dto.cidade();
        }
        if(estado != null) {
            this.estado = dto.estado();
        }
        if(cep != null) {
            this.cep = dto.cep();
        }
    }
}
