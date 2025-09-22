package com.example.pi_projectsoft.service;

import com.example.pi_projectsoft.model.Livro;
import com.example.pi_projectsoft.repository.LivroRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LivroServiceTest {

    private final LivroRepository livroRepository = Mockito.mock(LivroRepository.class);
    private final LivroService livroService = new LivroService(livroRepository);

    @Test
    void deveSalvarLivro() {
        Livro livro = new Livro();
        livro.setTitulo("Teste");

        Mockito.when(livroRepository.save(livro)).thenReturn(livro);

        Livro salvo = livroService.salvar(livro);
        assertEquals("Teste", salvo.getTitulo());
    }

    @Test
    void deveListarLivros() {
        Livro livro = new Livro();
        livro.setTitulo("Teste");

        Mockito.when(livroRepository.findAll()).thenReturn(Arrays.asList(livro));

        assertFalse(livroService.listarTodos().isEmpty());
    }

    @Test
    void deveDeletarLivro() {
        Livro livro = new Livro();
        livro.setId(1L);

        Mockito.when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));

        boolean resultado = livroService.deletarPorId(1L);
        assertTrue(resultado);
    }
}
