package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.SimpleTax;

public interface SimpleTaxRepository extends JpaRepository<SimpleTax, Integer> {
	
}
