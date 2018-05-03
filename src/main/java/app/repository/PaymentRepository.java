package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
}
