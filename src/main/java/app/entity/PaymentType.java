package app.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "payment_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PaymentType extends UrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "title", length = 30)
	private String title;

	@Column(name = "is_percent", nullable = false)
	private boolean isPercent = false;
	
	@Column(name = "is_bank_comission", nullable = false)
	private boolean isBankComission = false;
	
	@Column(name = "alias", length = 30)
	private String alias;
	
	public boolean isBankComission() {
		return isBankComission;
	}

	public void setBankComission(boolean isBankComission) {
		this.isBankComission = isBankComission;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setPercent(boolean isPercent) {
		this.isPercent = isPercent;
	}

	public PaymentType() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean getIsPercent() {
		return isPercent;
	}

	public void setIsPercent(boolean isPercent) {
		this.isPercent = isPercent;
	}

}
