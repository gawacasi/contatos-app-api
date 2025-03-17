package com.gawa.contatos_app.service;

import com.gawa.contatos_app.entity.Contato;
import com.gawa.contatos_app.repository.ContatoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;

    @Transactional
    public Contato salvar(Contato contato){
        return contatoRepository.save(contato);
    }

}
