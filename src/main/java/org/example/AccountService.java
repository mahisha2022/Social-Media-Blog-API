package org.example;

public class AccountService {

    private AccountDAO accountDAO;



    /**
     * no-args constructor for creating a new AccountService with a new AccountDAO,
     * this no-args constructor will be identified by the Controller service
     */

    public AccountService() {
        this.accountDAO = new AccountDAO();

    }

    /**
     * Controller for AccountService when AccountDAO is provided
     *
     * @param accountDAO
     */

    /**
     * @return the persisted account
     */

    public Account addNewUser(Account account) {
        if (account.getUsername().length() > 0 && isAccountUnique(account.getUsername())
                && isAccountUnique(account.getUsername()) && account.getPassword().length() >= 4) {
            Account newAct = accountDAO.insertNewAccountUser(account);
            return newAct;
        } else {
            return null;
        }

    }

    private boolean isAccountUnique(String username) {
        if (accountDAO.getAccountByUserName(username) == null) {
            return true;
        }
        return false;
    }


    public Account login(Account account) {
        if(accountDAO.accountLogin(account) != null){
            Account newAcct = accountDAO.accountLogin(account);
            return newAcct;
        }
        else {
            return null;
        }

    }





}
