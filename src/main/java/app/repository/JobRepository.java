package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {
	
}
