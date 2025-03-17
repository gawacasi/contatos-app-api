package com.gawa.contatos_app.service;

import com.gawa.contatos_app.entity.Contato;
import com.gawa.contatos_app.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;

    @Transactional
    public Contato salvar(Contato contato){
        return contatoRepository.save(contato);
    }

    @Transactional(readOnly = true)
    public Contato buscarPorId(long contatoId) {
        return contatoRepository.findById(contatoId).orElseThrow(
                () -> new RuntimeException("Contato nao encontrado.")
        );
    }

    @Transactional
    public Contato atualizarParcialmente(Long contatoId, Map<String, Object> updates) {
        Contato contato = contatoRepository.findById(contatoId)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado"));

        updates.forEach((campo, valor) -> {
            Field field = ReflectionUtils.findField(Contato.class, campo);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, contato, valor);
            }
        });

        return contatoRepository.save(contato);
    }

    @Transactional(readOnly = true)
    public List<Contato> buscarTodos() {
        return contatoRepository.findAll();
    }

    @Transactional
    public String deletar(Long contatoId) {
        Contato contato = contatoRepository.findById(contatoId)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado"));

        String nomeContato = contato.getContato_nome();
        contatoRepository.delete(contato);

        return nomeContato;
    }
}
