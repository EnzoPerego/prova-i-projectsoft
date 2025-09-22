package com.example.pi_projectsoft.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.pi_projectsoft.model.Livro;
import com.example.pi_projectsoft.service.LivroService;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class BiblioController {

    private final LivroService livroService;

    public BiblioController(LivroService livroService) {
        this.livroService = livroService;
    }

    // POST /livros
    @PostMapping
    public ResponseEntity<Livro> cadastrarLivro(@RequestBody Livro livro) {
        return ResponseEntity.ok(livroService.salvar(livro));
    }

    // GET /livros
    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros() {
        return ResponseEntity.ok(livroService.listarTodos());
    }

    // DELETE /livros/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarLivro(@PathVariable Long id) {
        boolean deletado = livroService.deletarPorId(id);
        if (deletado) {
            return ResponseEntity.ok("Livro deletado com sucesso!");
        } else {
            return ResponseEntity.status(404).body("Livro não encontrado!");
        }
    }
}
