package it.fperfetti.asos.banca.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "WITHDRAWALS")
public class Withdrawal {
	
	@Id @GeneratedValue
	private Long id;
	public Long getId(){ return id; }
	public void setId(Long id){ this.id = id; }
	
	@OneToOne
	private Account account;
	public Account getAccount() { return account; }
	public void setAccount(Account account) { this.account = account; }
	
	@Column(name = "AMOUNT")
	private Double amount;
	public Double getAmount() { return amount; }
	public void setAmount(Double amount) { this.amount = amount; }
	
	@Column(name = "VENDOR")
	private String vendor;
	public String getVendor() { return vendor; }
	public void setVendor(String vendor) { this.vendor = vendor; }
	
	@Temporal(TemporalType.DATE) @NotNull @Column(updatable=false)
	private Date timestamp;
	public Date getDate() { return timestamp; }
	public void setDate(Date timestamp) { this.timestamp = timestamp; }	
}
