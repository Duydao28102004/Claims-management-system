package System;

public class BankingInfo {
    private String bankName;
    private String accountName;
    private String accountNumber;

    public BankingInfo() {
        bankName = "default";
        accountName = "default";
        accountNumber = "default";
    }

    public BankingInfo(String bankName, String accountName, String accountNumber) {
        this.bankName = bankName;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
