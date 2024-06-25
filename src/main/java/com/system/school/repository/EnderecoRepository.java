package com.system.school.repository;

import com.system.school.domain.Aluno;
import com.system.school.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
