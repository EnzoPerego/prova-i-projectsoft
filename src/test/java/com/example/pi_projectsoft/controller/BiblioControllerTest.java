package com.example.pi_projectsoft.controller;

import com.example.pi_projectsoft.model.Livro;
import com.example.pi_projectsoft.service.LivroService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BiblioController.class)
class BiblioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LivroService livroService;

    @Test
    void deveListarLivros() throws Exception {
        Livro livro = new Livro();
        livro.setTitulo("Teste");
        livro.setAutor("Autor");

        Mockito.when(livroService.listarTodos()).thenReturn(Arrays.asList(livro));

        mockMvc.perform(get("/livros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Teste"));
    }

    @Test
    void deveCadastrarLivro() throws Exception {
        Livro livro = new Livro();
        livro.setTitulo("Novo Livro");

        Mockito.when(livroService.salvar(Mockito.any(Livro.class))).thenReturn(livro);

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"Novo Livro\",\"autor\":\"Autor\",\"genero\":\"Ficção\",\"anoPublicacao\":2020}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Novo Livro"));
    }

    @Test
    void deveDeletarLivroComSucesso() throws Exception {
        Mockito.when(livroService.deletarPorId(1L)).thenReturn(true);

        mockMvc.perform(delete("/livros/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Livro deletado com sucesso!"));
    }

    @Test
    void deveRetornarNotFoundQuandoLivroNaoExiste() throws Exception {
        Mockito.when(livroService.deletarPorId(99L)).thenReturn(false);

        mockMvc.perform(delete("/livros/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Livro não encontrado!"));
    }
}
