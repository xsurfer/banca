package it.fperfetti.asos.banca.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNTS")
public class Account {
	
	@Id @GeneratedValue
	private Long id;
	public Long getId(){ return id; }
	public void setId(Long id){ this.id = id; }
	
	@Column(name = "NAME")
	private String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@Column(name = "SURNAME")
	private String surname;
	public String getSurname() { return surname; }
	public void setSurname(String surname) { this.surname = surname; }
	
	@Column(name = "BALANCE")
	private Double balance;
	public Double getBalance() { return balance; }
	public void setBalance(Double balance) { this.balance = balance; }
	
	@OneToMany(mappedBy = "account")
	private List<CreditCard> cards = new ArrayList<CreditCard>();
	public List<CreditCard> getCards() { return cards; }
	public void setCards(List<CreditCard> cards) { this.cards = cards; }
	
}
