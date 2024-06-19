package com.system.school.domain;

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
    private List<Aluno> alunos;
}
