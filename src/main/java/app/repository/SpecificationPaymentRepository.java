package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.entity.SpecificationPayment;

public interface SpecificationPaymentRepository extends JpaRepository<SpecificationPayment, Integer> {
	
}
