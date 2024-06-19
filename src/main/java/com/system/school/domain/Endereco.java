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
}
