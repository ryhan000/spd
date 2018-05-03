package app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "persistent_logins")
public class PersistentLogin {
	
	@Column(name = "username", length = 64, nullable = false)
	private String username;
	
	@Id
	@Column(name = "series", length = 64)
	private String series;
	
	@Column(name = "token", length = 64, nullable = false)
	private String token;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_used", nullable = false, updatable = false, 
			columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date lastUsed;
	
	

}
