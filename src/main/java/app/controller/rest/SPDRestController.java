package app.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import app.entity.SPD;
import app.repository.SPDRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/spd")
public class SPDRestController {
	
	private static final String PARAM_ID = "id";
	private static final String HEADER_JSON = "Accept=application/json";
	
	@Autowired
	private SPDRepository spdRepository;
	
	@RequestMapping(value = "", method = RequestMethod.GET, headers = HEADER_JSON) 
	public ResponseEntity<List<SPD>> getAllSpd() {
		List<SPD> spds = spdRepository.findAll();
		if(spds.size() == 0) {
			return new ResponseEntity<List<SPD>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SPD>>(spds, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = HEADER_JSON) 
	public ResponseEntity<SPD> getSPDById(@PathVariable(PARAM_ID) int id) {
		SPD spd = spdRepository.findOne(id);
		if(spd == null) {
			return new ResponseEntity<SPD>(spd, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SPD>(spd, HttpStatus.FOUND);
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Void> addSpd(@RequestBody SPD spd) {
		spd = spdRepository.save(spd);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = HEADER_JSON)
	public ResponseEntity<Void> updateSpd(@PathVariable(PARAM_ID) int id, @RequestBody SPD spd) {
		spd.setId(id);
		spd.setVersion(spdRepository.getOne(id).getVersion());
		spd = spdRepository.save(spd);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = HEADER_JSON)
	public ResponseEntity<Void> deleteSpd(@PathVariable(PARAM_ID) int id, @RequestBody SPD spd) {
		spd.setId(id);
		spd.setVersion(spdRepository.getOne(id).getVersion());
		spdRepository.delete(spd);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

}
