package com.gawa.contatos_app.repository;

import com.gawa.contatos_app.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}