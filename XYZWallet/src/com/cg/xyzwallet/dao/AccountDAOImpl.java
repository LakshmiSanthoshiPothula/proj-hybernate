package com.cg.xyzwallet.dao;

import javax.persistence.EntityManager;

import com.cg.xyzwallet.bean.AccountBean;

public class AccountDAOImpl implements IAccountDao {

	
	private EntityManager em;
	
	@Override
	public boolean createAccount(AccountBean accountBean) throws Exception {
		try{
			
			this.em=JPAManager.createEntityManager();
			em.getTransaction().begin();
			System.out.println("dbvg");
			
			em.persist(accountBean);
			
			em.getTransaction().commit();
		JPAManager.closeResources(em);
			
			return true;
		}catch(Exception e){
		
			e.printStackTrace();
			return false;
		}
	
	}

	@Override
	public boolean updateAccount(AccountBean accountBean) throws Exception {
		try{
			this.em=JPAManager.createEntityManager();
			em.getTransaction().begin();
			
			em.merge(accountBean);
			
			em.getTransaction().commit();
			JPAManager.closeResources(em);
			return true;
		}catch(Exception e){
			return false;
		}
	
	}

	@Override
	public AccountBean findAccount(int accountId) throws Exception {
		try{
			em=JPAManager.createEntityManager();
			AccountBean accountBean2=em.find(AccountBean.class,accountId);
			JPAManager.closeResources(em);
			return accountBean2;
			
		}catch(Exception e){
			return null;
		}
	}

}
