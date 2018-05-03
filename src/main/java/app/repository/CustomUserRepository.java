package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.CustomUser;

public interface CustomUserRepository extends JpaRepository<CustomUser, Integer> {

	public CustomUser findByUsername(String username);
	
}
