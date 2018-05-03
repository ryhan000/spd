package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.SPD;

public interface SPDRepository extends JpaRepository<SPD, Integer> {
	
}
