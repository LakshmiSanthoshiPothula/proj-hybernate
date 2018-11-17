package com.cg.xyzwallet.service;

import com.cg.xyzwallet.bean.AccountBean;
import com.cg.xyzwallet.dao.AccountDAOImpl;
import com.cg.xyzwallet.dao.IAccountDao;
import com.cg.xyzwallet.exception.CustomerException;
import com.cg.xyzwallet.exception.CustomerExceptionMessage;

public class AccountServiceImpl implements IAccountService{
	private IAccountDao dao=new AccountDAOImpl();
	@Override
	public boolean createAccount(AccountBean accountBean)
			throws Exception {

		boolean result=dao.createAccount(accountBean);
		return result;
	}

	

	@Override
	public boolean deposit(AccountBean accountBean, double depositAmount)
			throws Exception {
		accountBean.setBalance(accountBean.getBalance()+depositAmount);

		boolean result=dao.updateAccount(accountBean);
		return result;
	}

	@Override
	public boolean withdraw(AccountBean accountBean, double withdrawAmount)
			throws Exception {
		accountBean.setBalance(accountBean.getBalance()-withdrawAmount);

		boolean result=dao.updateAccount(accountBean);
		return result;
	}

	@Override
	public boolean fundTransfer(AccountBean transferingAccountBean,
			AccountBean beneficiaryAccountBean, double transferAmount) throws Exception {
		
		transferingAccountBean.setBalance(transferingAccountBean.getBalance()-transferAmount);
		beneficiaryAccountBean.setBalance(beneficiaryAccountBean.getBalance()+transferAmount);
		
	
		boolean result1=dao.updateAccount(transferingAccountBean);
		boolean result2=dao.updateAccount(beneficiaryAccountBean);
		return result1 && result2;
	}

	



	@Override
	public AccountBean findAccount(int accountId) throws Exception {
		
		AccountBean bean=dao.findAccount(accountId);
		return bean;
	}
	
	@Override
    public boolean validationDetails(AccountBean accountBean) throws CustomerException{
           // TODO Auto-generated method stub
           boolean isValid=true;
           
           if(!( accountBean.getCustomerBean().getFirstName().matches("[a-zA-Z]{3,}")))
           {
                  
                 throw new CustomerException(CustomerExceptionMessage.ERROR1);
           }
           if(!( accountBean.getCustomerBean().getLastName().matches("[a-zA-Z]{3,}")))
           {
            
                  throw new CustomerException(CustomerExceptionMessage.ERROR2);
           }
           if(!(accountBean.getCustomerBean().getPhoneNo().toString().matches("^[6-9][0-9]{9}"))){
                  
               
                  throw new CustomerException(CustomerExceptionMessage.ERROR3);
           }
           if((accountBean.getCustomerBean().getEmailId() == null || !(accountBean.getCustomerBean().getEmailId().matches("[a-zA-Z][a-zA-z0-9-.]*@[a-zA-Z0-9]+([.][a-zA-Z)]+)+")))){

          
                  throw new CustomerException(CustomerExceptionMessage.ERROR4);
           }
           if((accountBean.getCustomerBean().getPanNum()==null) || (!(accountBean.getCustomerBean().getPanNum().matches("^[A-Z][A-Z0-9]{9}")))){
                  
             
                  throw new CustomerException(CustomerExceptionMessage.ERROR5);
           }
           if((accountBean.getCustomerBean().getAddress()==null)||(!(accountBean.getCustomerBean().getAddress().matches("[A-Za-z ]{5,50}"))))
           {
             
                  throw new CustomerException(CustomerExceptionMessage.ERROR6);
           }
           if(!(accountBean.getBalance()>0)){
               
                  throw new CustomerException(CustomerExceptionMessage.ERROR7);
    
           }
           return isValid;
           
    }



}
