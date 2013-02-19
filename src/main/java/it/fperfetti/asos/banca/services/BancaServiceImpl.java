package it.fperfetti.asos.banca.services;

import it.fperfetti.asos.banca.model.Account;
import it.fperfetti.asos.banca.model.CreditCard;
import it.fperfetti.asos.banca.model.Withdrawal;
import it.fperfetti.asos.banca.util.HibernateUtil;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Path("/")
public class BancaServiceImpl implements BancaService {

	public BancaServiceImpl(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();		
			@SuppressWarnings("unchecked")
			List<Account> account =  session.createQuery("from Account as account")
				.list();
			if(account.size()==0){
				Account tmp = new Account();
				tmp.setName("Fabio");
				tmp.setSurname("Perfetti");
				tmp.setBalance(500.0);
				CreditCard cc = new CreditCard();
				cc.setAccount(tmp);
				cc.setCardNumber("1234567812345678");
				cc.setCircuit("visa");
				cc.setCvv("123");
				tmp.getCards().add(cc);
				
				CreditCard cc2 = new CreditCard();
				cc2.setAccount(tmp);
				cc2.setCardNumber("9234567812345679");
				cc2.setCircuit("mastercard");
				cc2.setCvv("456");
				tmp.getCards().add(cc2);
				
				session.persist(tmp);
				session.persist(cc);
				session.persist(cc2);
			}			
			tx.commit();
		}
		catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}

		
	}
	
	@Override
	@GET
	@Produces("application/json")
	@Path("/check/{cardnumber}/{cvv}/{owner_name}/{owner_surname}")
	public Long isValid(
			@PathParam("cardnumber") String cardnumber,
			@PathParam("cvv") String cvv,
			@PathParam("owner_name") String owner_name, 
			@PathParam("owner_surname") String owner_surname) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Long accountID = new Long(-1);
		try {
			tx = session.beginTransaction();		
			@SuppressWarnings("unchecked")
			List<CreditCard> cards =  session.createQuery("from CreditCard as card where card.cardNumber = :cardnumber")
			.setString("cardnumber", cardnumber)
			.list();
			if(cards.size()>1){
				throw new Exception("More than one card with that number!");
			}
			else if(cards.size()==1){
				CreditCard card = cards.get(0);
				if(cvv.compareTo(card.getCvv())==0){
					Account account = card.getAccount();
					if(account.getName().compareToIgnoreCase(owner_name)==0 && account.getSurname().compareToIgnoreCase(owner_surname)==0){
						accountID = account.getId();
					}
					else{
						throw new Exception("Wrong owner");
					}
				}
				else {
					throw new Exception("Wrong cvv");
				}
			}			
			tx.commit();
		}
		catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return accountID;
	}

	@Override
	@GET
	@Produces("application/json")
	@Path("/withdrawal/{vendor}/{id}/{amount}")
	public Boolean withdrawal(
			@PathParam("vendor") String vendor,
			@PathParam("id") Long accountId,
			@PathParam("amount") Double amount) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Boolean ret = false;
		try {
			tx = session.beginTransaction();		
			Account account =  (Account) session.get(Account.class, accountId);
			if(account==null){
				throw new Exception("Account doesn't exist!");
			}
			else if(account.getBalance()>=amount){
				Withdrawal withdrawal = new Withdrawal();
				withdrawal.setAccount(account);
				withdrawal.setAmount(amount);
				withdrawal.setDate(new Date());
				withdrawal.setVendor(vendor);
				session.persist(withdrawal);
				account.setBalance(account.getBalance()-amount);
			}		
			tx.commit();
			ret = true;
		}
		catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}

		return ret;
	}


}
