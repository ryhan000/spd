package app.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name ="agreement_tarif")
@NamedQuery(name = AgreementTarif.FIND_AGREEMENT_TARIF_BY_SPECIFICATION_ID, 
			query = "select a from AgreementTarif a where a.agreement.id = (" +
					"select s.agreement.id from Specification s where s.id = :specificationId) and a.dateStart = (" +
					"select max(a.dateStart) from AgreementTarif a where a.agreement.id = (" +
					"select s.agreement.id from Specification s where s.id = :specificationId) and a.dateStart <= (" +
					"select s.dateStart from Specification s where s.id = :specificationId))")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AgreementTarif extends UrlEntity implements Serializable {

	public static final String FIND_AGREEMENT_TARIF_BY_SPECIFICATION_ID = "AgreementTarif.findAgreementTarifBySpecificationId";
	private static final long serialVersionUID = 1L;

	@ManyToOne()
	@JoinColumn(name = "agreement_id")
	@JsonBackReference
	private Agreement agreement;

	@Column(name = "configuring", columnDefinition=DECIMAL_10_2_DEFAULT_0_00)
	private Double configuring;

	@Column(name = "programming", columnDefinition=DECIMAL_10_2_DEFAULT_0_00)
	private Double programming;

	@Column(name = "architecting", columnDefinition=DECIMAL_10_2_DEFAULT_0_00)
	private Double architecting;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_start")
	private Date dateStart;

	public AgreementTarif() {
	}

	public AgreementTarif(Agreement agreement, Double configuring, Double programming, Double architecting,
			Date datestart) {
		super();
		this.agreement = agreement;
		this.configuring = configuring;
		this.programming = programming;
		this.architecting = architecting;
		this.dateStart = datestart;
	}

	public Agreement getAgreement() {
		return agreement;
	}

	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}

	public Double getConfiguring() {
		return configuring;
	}

	public void setConfiguring(Double configuring) {
		this.configuring = configuring;
	}

	public Double getProgramming() {
		return programming;
	}

	public void setProgramming(Double programming) {
		this.programming = programming;
	}

	public Double getArchitecting() {
		return architecting;
	}

	public void setArchitecting(Double architecting) {
		this.architecting = architecting;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	
}
