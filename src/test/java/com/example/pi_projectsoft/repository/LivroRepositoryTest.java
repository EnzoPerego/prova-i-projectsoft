package com.example.pi_projectsoft.repository;

import com.example.pi_projectsoft.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Test
    void deveSalvarELerLivro() {
        Livro livro = new Livro();
        livro.setTitulo("Livro Teste");
        livro.setAutor("Autor Teste");
        livro.setGenero("Ficção");
        livro.setAnoPublicacao(2020);

        livroRepository.save(livro);

        List<Livro> livros = livroRepository.findAll();
        assertFalse(livros.isEmpty());
        assertEquals("Livro Teste", livros.get(0).getTitulo());
    }
}
