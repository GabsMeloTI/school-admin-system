package com.system.school.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.system.school.dto.aluno.AlterarAlunoDto;
import com.system.school.dto.aluno.CadastroAlunoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "tb_aluno")
public class Aluno {
    @Id
    @Column(name = "cd_aluno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(name = "nm_aluno", nullable = false, length = 100)
    private String nome;

    @Column(name = "dt_nascimento", nullable = false)
    private LocalDate nascimento;

    @Column(name = "ds_foto", nullable = false, length = 1000)
    private String foto;

    @ManyToOne
    @JoinColumn(name = "cd_curso")
    @JsonBackReference
    private Curso curso;

    @OneToOne
    @JoinColumn(name = "cd_endereco", nullable = false)
    @JsonBackReference
    private Endereco endereco;

    @OneToOne
    @JoinColumn(name = "cd_contato", nullable = false)
    @JsonBackReference
    private Contato contato;

    public Aluno(CadastroAlunoDto dto) {
        this.nome = dto.nome();
        this.nascimento = dto.nascimento();
        this.foto = dto.foto();
    }

    public void alterar(AlterarAlunoDto dto) {
        if(dto.foto() != null) {
            this.foto = dto.foto();
        }
    }
}
