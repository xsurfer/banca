package it.fperfetti.asos.banca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CREDIT_CARDS")
public class CreditCard {

	@Id @GeneratedValue
	private Long id;
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
	public Account getAccount() { return account; }
	public void setAccount(Account account) { this.account = account; }
	
	@Column(name = "CARDNUMBER")
	private String cardNumber;
	public String getCardNumber() { return cardNumber; }
	public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
	
	@Column(name = "CVV")
	private String cvv;
	public String getCvv() { return cvv; }
	public void setCvv(String cvv) { this.cvv = cvv; }
	
	@Column(name = "CIRCUIT")
	private String circuit;
	public String getCircuit() { return circuit; }
	public void setCircuit(String circuit) { this.circuit = circuit; }
	
}
