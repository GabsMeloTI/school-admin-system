package com.system.school.domain;
import com.system.school.dto.aluno.AlterarAlunoDto;
import com.system.school.dto.aluno.ListagemAlunoDto;
import com.system.school.dto.contato.AlterarContatoDto;
import com.system.school.dto.contato.CadastroContatoDto;
import com.system.school.dto.contato.ListagemContatoDto;
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

    @OneToOne(mappedBy = "contato", cascade = CascadeType.ALL)
    private Aluno aluno;

    public Contato(CadastroContatoDto dto) {
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.tipo = dto.tipo();
    }

    public void alterar(AlterarContatoDto dto) {
        if(telefone != null) {
            this.telefone = dto.telefone();
        }
        if(email != null) {
            this.email = dto.email();
        }
        if(tipo != null) {
            this.tipo = dto.tipo();
        }
    }
}

