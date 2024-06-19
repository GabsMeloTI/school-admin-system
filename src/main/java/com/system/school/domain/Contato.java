package com.system.school.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "tb_contato")
public class Contato {
    @Id
    @Column(name = "cd_contato")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(name = "nr_telefone", nullable = false, length = 11)
    private String telefone;

    @Column(name = "ds_email", nullable = false, length = 150)
    private String email;

    @Column(name = "tp_contato", nullable = false)
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "cd_aluno", nullable = false)
    private Aluno aluno;
}

