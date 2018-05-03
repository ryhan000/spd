package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.entity.PaymentType;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer> {
	
}
