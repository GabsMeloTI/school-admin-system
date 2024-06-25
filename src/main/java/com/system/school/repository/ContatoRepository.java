package com.system.school.repository;

import com.system.school.domain.Aluno;
import com.system.school.domain.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {
}
