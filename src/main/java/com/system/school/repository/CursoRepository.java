package com.system.school.repository;

import com.system.school.domain.Aluno;
import com.system.school.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
