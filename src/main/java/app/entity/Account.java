package app.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "account")
@NamedQuery(name = Account.FIND_ACTUAL_SPD_ACCOUNT_BY_SPD_ID, query = "select ac from Account ac where ac.spd.id = :spdId")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Account extends UrlEntity implements Serializable {

	public static final String FIND_ACTUAL_SPD_ACCOUNT_BY_SPD_ID = "Account.findActualSpdAccountBySpdId";
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "spd_id")
	@JsonBackReference(value = "spd-account")
	private SPD spd;

	@Column(name = "account_number", length = 35)
	private String accountNumber;

	@Column(name = "mfo", length = 10)
	private String mfo;

	@Column(name = "bank_name", length = 150)
	private String bankName;

	public Account() {
	}

	public Account(SPD spd, String accountNumber, String mfo, String bankName) {
		this.spd = spd;
		this.accountNumber = accountNumber;
		this.mfo = mfo;
		this.bankName = bankName;
	}

	public SPD getSpd() {
		return spd;
	}

	public void setSpd(SPD spd) {
		this.spd = spd;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getMfo() {
		return mfo;
	}

	public void setMfo(String mfo) {
		this.mfo = mfo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@JsonIgnore
	public String getPresentation() {
		StringBuilder accountView = new StringBuilder();
		accountView.append(this.accountNumber);
		accountView.append(", в " + this.bankName);
		accountView.append(", МФО " + this.mfo);
		return accountView.toString();
	}

	@JsonIgnore
	@Override
	public String toString() {
		StringBuilder accountViewBuilder = new StringBuilder();
		accountViewBuilder.append(this.accountNumber);
		accountViewBuilder.append(", в " + this.bankName);
		accountViewBuilder.append(", МФО " + this.mfo);
		return accountViewBuilder.toString();
	}

}
