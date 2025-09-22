package com.example.pi_projectsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pi_projectsoft.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
