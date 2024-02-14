package atm;

import atm.ClientType;

import java.time.LocalDate;

public class Client {
    private int ID;
    private int pin;
    private String name;
    private String surname;
    private double balance;
    private double dailyLimitWithdrawal;
    private double dailyLimitDeposit;
    private LocalDate validUntil;
    private ClientType clientType;

    private boolean loggedIn;

    public Client(int id, int pin, String name, String surname, double dailyLimitWithdrawal, double dailyLimitDeposit, LocalDate validUntil, ClientType clientType) {
        this.ID = id;
        this.setPin(pin);
        this.setName(name);
        this.setSurname(surname);
        this.setBalance(0);
        this.dailyLimitWithdrawal = dailyLimitWithdrawal;
        this.dailyLimitDeposit = dailyLimitDeposit;
        this.setValidUntil(validUntil);
        this.clientType = clientType;
        loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean login(int pin){
        boolean loginAttempt = this.pin == pin;
        setLoggedIn(loginAttempt);
        return loginAttempt;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new NullPointerException("Name cannot be empty");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(surname == null || surname.trim().isEmpty()){
            throw new NullPointerException("Name cannot be empty");
        }
        this.surname = surname;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if(balance < 0 && this.clientType == ClientType.DEBIT){
            throw new IllegalArgumentException("Balance cannot be below zero for debit clients");
        }
        this.balance = balance;
    }
    public LocalDate getValidUntil() {
        return validUntil;
    }
    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        if(pin < 1000 || pin > 9999){
            throw new IllegalArgumentException("PIN can only have 4 digits");
        }
        this.pin = pin;
    }
    public double getDailyLimitWithdrawal() {
        return dailyLimitWithdrawal;
    }
    public void setDailyLimitWithdrawal(double dailyLimitWithdrawal) {
        if(dailyLimitWithdrawal <= 0){
            throw new IllegalArgumentException("Daily limit withdrawal has to be > 0");
        }
        this.dailyLimitWithdrawal = dailyLimitWithdrawal;
    }
    public double getDailyLimitDeposit() {
        return dailyLimitDeposit;
    }
    public void setDailyLimitDeposit(double dailyLimitDeposit) {
        if(dailyLimitDeposit <= 0){
            throw new IllegalArgumentException("Daily limit deposit has to be > 0");
        }
        this.dailyLimitDeposit = dailyLimitDeposit;
    }
    public ClientType getClientType() {
        return clientType;
    }
    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }
    public void setValidUntil(LocalDate validUntil) {
        if(!validUntil.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Validity date should be after current date");
        }
        this.validUntil = validUntil;
    }
    public void depositMoney(double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("Amount has to be > 0");
        }
        if(amount > this.dailyLimitDeposit){
            throw new ArithmeticException("You cannot deposit more than the limit");
        }
        this.balance += amount;
    }
    public void withdrawMoney(double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("Amount has to be > 0");}
        if(amount > this.balance && this.clientType == ClientType.DEBIT){
            throw new ArithmeticException("Not enough money on the debit account");}
        if(amount > this.dailyLimitWithdrawal){
            throw new IllegalStateException("You cannot withdraw more than the limit");}
        this.balance -= amount;
    }

    public void transferMoney(Client client, double amount){
        this.withdrawMoney(amount);
        client.depositMoney(amount);}
}
