package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    AccountDAO AccountDAO;

    /**
     * No-args constructor for a AccountService instantiates a plain AccountDAO.
     * There is no need to modify this constructor.
     */
    public AccountService(){
        AccountDAO = new AccountDAO();
    }

    /**
     * Constructor for a AccountService when a AccountDAO is provided.
     * This is used for when a mock AccountDAO that exhibits mock behavior is used in the test cases.
     * This would allow the testing of AccountService independently of AccountDAO.
     * There is no need to modify this constructor.
     * @param AccountDAO
     */
    public AccountService(AccountDAO AccountDAO){
        this.AccountDAO = AccountDAO;
    }

    public Account addAccount(Account Account){
        if (Account.username=="" || Account.password.length()<4) {
            return null;
        }
        return AccountDAO.insertAccount(Account);
    }

    public Account getAccountByUserAccount(Account Account){
        if (AccountDAO.getAccountByUsername(Account.username)==null || !AccountDAO.getAccountByUsername(Account.username).getPassword().equals(Account.password)) {
            return null;
        }
        return AccountDAO.getAccountByUsername(Account.username);
    }

}