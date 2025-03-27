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
    public Contato atualizarParcialmente(Long id, Map<String, Object> updates) {
        Contato contato = contatoRepository.findById(id).orElseThrow(() -> new RuntimeException("Contato não encontrado"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "contato_sn_favorito":
                    contato.setContato_sn_favorito(Contato.Role.valueOf(value.toString())); // Converte para Role
                    break;
                case "contato_sn_ativo":
                    contato.setContato_sn_ativo(Contato.Role.valueOf(value.toString())); // Converte para Role
                    break;
                case "contato_nome":
                    contato.setContato_nome(value.toString());
                    break;
                case "contato_email":
                    contato.setContato_email(value.toString());
                    break;
                case "contato_celular":
                    contato.setContato_celular(value.toString());
                    break;
                case "contato_telefone":
                    contato.setContato_telefone(value.toString());
                    break;
            }
        });

        return contatoRepository.save(contato); // Salva a entidade atualizada
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
