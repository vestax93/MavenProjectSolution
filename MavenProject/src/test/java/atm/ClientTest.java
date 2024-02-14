package atm;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ClientTest {
    private static final int ID = 1;
    private static final int PIN = 1234;
    private static final int WRONG_PIN = 4321;
    private static final int SMALLER_PIN = 123;
    private static final int BIGGER_PIN = 10001;
    private static final String NAME = "Anne";
    private static final String SURNAME = "Hart";
    private static final String EMPTY_STRING = "";
    private static final double AMOUNT = 50;
    private static final double BALANCE = 100;
    private static final double NUMBER_BELOW_ZERO = -50;
    private static final double AMOUNT_ABOVE_LIMIT = 2000;
    private static final double DAILY_LIMIT_DEPOSIT = 1000;
    private static final double DAILY_LIMIT_WITHDRAWAL = 1500;
    private static final LocalDate DATE_VALID_UNTIL = LocalDate.of(2028, 1, 1);
    private static final LocalDate INVALID_DATE = LocalDate.of(2020, 1, 1);
    private static final ClientType CLIENT_TYPE = ClientType.DEBIT;
    Client c1;
    private static final double DELTA = 0.0001;


    @Before
    public void setUp()  {
        c1 = new Client(ID,PIN,NAME,SURNAME,  DAILY_LIMIT_WITHDRAWAL, DAILY_LIMIT_DEPOSIT, DATE_VALID_UNTIL, CLIENT_TYPE);
    }

    @Test
    public void checkGetId(){
        assertEquals(ID, c1.getID());
    }
    @Test
    public void checkGetLogin(){
        assertFalse( c1.isLoggedIn());
    }

    @Test
    public void checkIfLoggedIn(){
        c1.login(PIN);
        assertTrue(c1.isLoggedIn());
    }
    @Test
    public void checkIfLoginFailed(){
        assertFalse(c1.login(WRONG_PIN));
    }



    @Test (expected = IllegalArgumentException.class)
    public void checkIfSmallerPinFails(){
        c1.setPin(SMALLER_PIN);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkIfBiggerPinFails(){
        c1.setPin(BIGGER_PIN);
    }

    @Test
    public void checkGetPin(){
        assertEquals(PIN, c1.getPin());
    }

    @Test (expected = NullPointerException.class)
    public void checkIfNameIsNull(){
        c1.setName(null);
    }
    @Test (expected = NullPointerException.class)
    public void checkIfNameIsEmptyString(){
        c1.setName(EMPTY_STRING);
    }
    @Test (expected = NullPointerException.class)
    public void checkIfSurnameIsNull(){
        c1.setSurname(null);
    }
    @Test (expected = NullPointerException.class)
    public void checkIfSurnameIsEmptyString(){
        c1.setSurname(EMPTY_STRING);
    }

    @Test
    public void checkGetName(){
        assertEquals(NAME, c1.getName());
    }
    @Test
    public void checkGetSurName(){
        assertEquals(SURNAME, c1.getSurname());
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkBalanceBelowZeroForDebitUser(){
        c1.setBalance(NUMBER_BELOW_ZERO);
    }

    @Test
    public void checkBalanceBelowZeroForCreditUser(){
        c1.setClientType(ClientType.CREDIT);
        c1.setBalance(NUMBER_BELOW_ZERO);
        assertTrue(NUMBER_BELOW_ZERO == c1.getBalance());
    }

    @Test
    public void checkGetBalance(){
        c1.setBalance(AMOUNT);
        assertEquals(AMOUNT,c1.getBalance(),DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkDateValidUntil(){
        c1.setValidUntil(INVALID_DATE);
    }

    @Test
    public void checkGetDate(){
        assertEquals(DATE_VALID_UNTIL, c1.getValidUntil());
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkDailyLimitWithdrawal(){
        c1.setDailyLimitWithdrawal(NUMBER_BELOW_ZERO);
    }

    @Test
    public void checkGetDailyLimitWithdrawal(){
        assertEquals(DAILY_LIMIT_WITHDRAWAL, c1.getDailyLimitWithdrawal(), DELTA);
    }


    @Test (expected = IllegalArgumentException.class)
    public void checkDailyLimitDeposit(){
        c1.setDailyLimitDeposit(NUMBER_BELOW_ZERO);
    }

    @Test
    public void checkGetDailyLimitDeposit(){
        assertEquals(DAILY_LIMIT_DEPOSIT, c1.getDailyLimitDeposit(), DELTA);
    }

    @Test
    public void checkClientType(){
        assertEquals(CLIENT_TYPE, c1.getClientType());
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkIfWithdrawalIsZero(){
        c1.withdrawMoney(0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkIfWithdrawalIsBelowZero(){
        c1.withdrawMoney(NUMBER_BELOW_ZERO);
    }

    @Test (expected = ArithmeticException.class)
    public void checkIfThereIsEnoughMoneyDebit(){
        c1.withdrawMoney(AMOUNT_ABOVE_LIMIT);
    }

    @Test
    public void checkIfCreditIsAllowed(){
        c1.setClientType(ClientType.CREDIT);
        c1.withdrawMoney(AMOUNT);
    }

    @Test (expected = ArithmeticException.class)
    public void checkIsItIsOverDailyLimitWithdrawal(){
        c1.withdrawMoney(DAILY_LIMIT_WITHDRAWAL + 1);
    }

    @Test
    public void checkIfMoneyIsWithdrawnFromBalance(){
        c1.setBalance(BALANCE);
        double newBalance = c1.getBalance() - AMOUNT;
        c1.withdrawMoney(AMOUNT);
        assertEquals(newBalance, c1.getBalance(), DELTA);
    }

    //deposit

    @Test (expected = IllegalArgumentException.class)
    public void checkIfDepositIsZero(){
        c1.depositMoney(0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkIfDepositIsBelowZero(){
        c1.depositMoney(NUMBER_BELOW_ZERO);
    }

    @Test (expected = ArithmeticException.class)
    public void checkIsItIsOverDailyLimitDeposit(){
        c1.depositMoney(DAILY_LIMIT_DEPOSIT + 1);
    }

    @Test
    public void checkIfMoneyIsDepositedToBalance(){
        double newBalance = c1.getBalance() + AMOUNT;
        c1.depositMoney(AMOUNT);
        assertEquals(newBalance, c1.getBalance(), DELTA);
    }

    @Test
    public void checkIfMoneyIsTransferred(){
        Client c2 = new Client(ID+1,PIN+1,NAME,SURNAME,  DAILY_LIMIT_WITHDRAWAL, DAILY_LIMIT_DEPOSIT, DATE_VALID_UNTIL, CLIENT_TYPE);
        c1.setBalance(BALANCE);
        double expectedBalanceC1 = c1.getBalance() - AMOUNT;
        double expectedBalanceC2 = c2.getBalance() + AMOUNT;
        c1.transferMoney(c2,AMOUNT);
        assertTrue(c1.getBalance() == expectedBalanceC1 && c2.getBalance() == expectedBalanceC2 );
    }










}