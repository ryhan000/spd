package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
}
