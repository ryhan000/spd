package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.SPD;
import app.repository.SPDRepository;

@Service
public class SPDService {
	
	@Autowired
	private SPDRepository spdRepository;

	public void save(SPD spd) {
		this.spdRepository.save(spd);
	}
	
	
}
