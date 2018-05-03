package app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "agreement")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Agreement extends UrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "spd_id")
	@JsonBackReference(value="spd-agreement")
	private SPD spd;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	@JsonBackReference
	private Company company;

	@Column(name = "number", length = 255)
	private String number;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_start")
	private Date dateStart;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "agreement")
	@OrderBy("id ASC")
	@JsonManagedReference
	private Set<AgreementTarif> tarifs;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "agreement")
	@OrderBy("id ASC")
	@JsonManagedReference
	private Set<Specification> specifications;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "agreement_file_id")
	private AgreementFile agreementFile;

	public Agreement() {
	}

	public Agreement(SPD spd, String number, Date dateStart) {
		this.spd = spd;
		this.number = number;
		this.dateStart = dateStart;
	}

	public Set<AgreementTarif> getTarifs() {
		return tarifs;
	}

	public void setTarifs(Set<AgreementTarif> tarifs) {
		this.tarifs = tarifs;
	}

	public Set<Specification> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(Set<Specification> specifications) {
		this.specifications = specifications;
	}

	public SPD getSpd() {
		return spd;
	}

	public void setSpd(SPD spd) {
		this.spd = spd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public AgreementFile getAgreementFile() {
		return agreementFile;
	}

	public void setAgreementFile(AgreementFile agreementFile) {
		this.agreementFile = agreementFile;
	}
	
}
