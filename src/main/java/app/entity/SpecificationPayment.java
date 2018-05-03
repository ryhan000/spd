package app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "specification_payment")
public class SpecificationPayment extends UrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "specification_id")
	@JsonBackReference
	private Specification specification;

	@Column(name = "payment_number")
	private Integer paymentNumber;

	@Column(name = "payment_sum", columnDefinition=DECIMAL_10_2_DEFAULT_0_00)
	private Double paymentSum;

	@Column(name = "payment_days")
	private Integer paymentDays;

	@Column(name = "comment", length = 255)
	private String comment;

	public SpecificationPayment() {
	}

	public SpecificationPayment(Specification specification, Integer paymentNumber, Double paymentSum,
			Integer paymentDays, String comment) {
		this.specification = specification;
		this.paymentNumber = paymentNumber;
		this.paymentSum = paymentSum;
		this.paymentDays = paymentDays;
		this.comment = comment;
	}

	public Specification getSpecification() {
		return specification;
	}

	public void setSpecification(Specification specification) {
		this.specification = specification;
	}

	public Integer getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(Integer paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	public Double getPaymentSum() {
		return paymentSum;
	}

	public void setPaymentSum(Double paymentSum) {
		this.paymentSum = paymentSum;
	}

	public Integer getPaymentDays() {
		return paymentDays;
	}

	public void setPaymentDays(Integer paymentDays) {
		this.paymentDays = paymentDays;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}