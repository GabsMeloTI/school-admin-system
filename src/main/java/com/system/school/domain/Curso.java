package com.system.school.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.system.school.dto.curso.AlterarCursoDto;
import com.system.school.dto.curso.CadastroCursoDto;
import com.system.school.dto.curso.ListagemCursoDto;
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
@Table(name = "tb_curso")
public class Curso {
    @Id
    @Column(name = "cd_curso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(name = "nm_curso", nullable = false, length = 100)
    private String nome;

    @Column(name = "ds_curso", nullable = false, length = 250)
    private String descricao;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Aluno> alunos;

    public Curso(CadastroCursoDto dto) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
    }


    public void alterar(AlterarCursoDto dto) {
        if(nome != null) {
            this.nome = dto.nome();
        }
        if(descricao != null) {
            this.descricao = dto.descricao();
        }
    }
}
