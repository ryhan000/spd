package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.ESVTax;

public interface ESVTaxRepository extends JpaRepository<ESVTax, Integer> {
	
}
