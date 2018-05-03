package app.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "agreement_file")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AgreementFile extends UrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "description", length = 255)
	private String description;
	
	@Column(name = "file_name", length = 255)
	private String filename;
	
	@Column(name = "file_data", columnDefinition = "LONGBLOB")
	private byte[] fileData;
	
	public AgreementFile() {
	}

	public AgreementFile(String description, String filename, byte[] fileData) {
		this.description = description;
		this.filename = filename;
		this.fileData = fileData;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getFile() {
		return fileData;
	}

	public void setFile(byte[] fileData) {
		this.fileData = fileData;
	}
	
}
