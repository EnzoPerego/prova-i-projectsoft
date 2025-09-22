package com.example.pi_projectsoft.service;

import org.springframework.stereotype.Service;
import com.example.pi_projectsoft.model.Livro;
import com.example.pi_projectsoft.repository.LivroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public boolean deletarPorId(Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            livroRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
