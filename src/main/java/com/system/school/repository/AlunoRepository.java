package com.system.school.repository;

import com.system.school.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    List<Aluno> findAllByOrderByNomeAsc();
    List<Aluno> findAllByOrderByNomeDesc();
}
