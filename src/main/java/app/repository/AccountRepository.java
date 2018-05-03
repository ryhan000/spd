package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import app.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	static final String PARAM_SPD_ID = "spdId";

	public Account findActualSpdAccountBySpdId(@Param(PARAM_SPD_ID) Integer spdId);
	
}