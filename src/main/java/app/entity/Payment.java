package app.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "payment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Payment extends UrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "spd_id")
	@JsonBackReference(value="spd-payment")
	private SPD spd;

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_type_id")
	private PaymentType paymentType;

	@Column(name = "value", columnDefinition=DECIMAL_10_2_DEFAULT_0_00)
	private Double value;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_start")
	private Date dateStart;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_finish")
	private Date dateFinish;

	public Payment() {
	}

	public Payment(SPD spd, PaymentType paymentType, Double value, Date dateStart, Date dateFinish) {
		this.spd = spd;
		this.paymentType = paymentType;
		this.value = value;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
	}

	public SPD getSpd() {
		return spd;
	}

	public void setSpdId(SPD spd) {
		this.spd = spd;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public void setSpd(SPD spd) {
		this.spd = spd;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}

}
