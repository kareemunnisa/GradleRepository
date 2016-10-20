package org.cap.test;

import static org.junit.Assert.*;

import org.cap.dao.AccountDao;
import org.cap.dto.Account;
import org.cap.dto.Address;
import org.cap.dto.Customer;
import org.cap.exception.InsufficientBalanceException;
import org.cap.service.AcccountService;
import org.cap.service.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BankAppTest {
	private AcccountService accService;
	@Mock
	private AccountDao accountDao;	
	
	@Before
	public void beforeMethod(){
		MockitoAnnotations.initMocks(this);
		accService = new AccountServiceImpl(accountDao);
	}
	
	@Test
	public void test_Withdrawal_Method() throws InsufficientBalanceException {
		Account account = new Account();
		account.setAccountNo(1111);
		account.setAmount(2000);
		Customer customer = new Customer();
		customer.setCustAddress(new Address());
		customer.setCustName("kari");
		account.setCustomer(customer);
		
		//Declaration
		Mockito.when(accountDao.findAccountById(1111)).thenReturn(account);
		
		//Business logic
		Account account2 = accService.withdraw(1111, 500);
		
		//verification
		Mockito.verify(accountDao).findAccountById(1111);
		
		assertEquals(1500, account2.getAmount(),0.0);
		
		
		
		
	}

}
