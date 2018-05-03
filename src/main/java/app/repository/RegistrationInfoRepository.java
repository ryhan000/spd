package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.RegistrationInfo;

public interface RegistrationInfoRepository extends JpaRepository<RegistrationInfo, Integer> {

}
