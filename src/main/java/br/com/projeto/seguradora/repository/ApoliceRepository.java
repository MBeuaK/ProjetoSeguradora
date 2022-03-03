package br.com.projeto.seguradora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.seguradora.model.Apolice;

@Repository
public interface ApoliceRepository extends JpaRepository<Apolice, Long> {
	
	Apolice findByNumeroApolice(Long random);
	
}
