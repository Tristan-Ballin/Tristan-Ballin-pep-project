package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;

public class AccountDAO {


    /**
     * TODO: Retrieve a specific Account using its Username.
     *
     * @param usernameString a Account username.
     */
    public Account getAccountByUsername(String usernameString){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM Account where username = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setString(1,usernameString);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Account Account = new Account(rs.getInt("account_id"), rs.getString("username"),
                        rs.getString("password"));
                return Account;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * TODO: Add a specific Account using its Username and Password.
     *
     * @param Account a Account object. the Account object does not contain a Account ID..
     */
    public Account insertAccount(Account Account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here. When inserting, you only need to define the departure_city and arrival_city
            //values (two columns total!)
            String sql = "INSERT INTO Account (username, password) VALUES (?, ?);" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setString(1,Account.username);
            preparedStatement.setString(2,Account.password);

            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_Account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_Account_id, Account.getUsername(), Account.getPassword());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
        /**
     * TODO: Retrieve a specific Account using its Id.
     *
     * @param id a Account ID.
     */
    public Account getAccountById(int id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM Account where account_id = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setInt(1,id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Account Account = new Account(rs.getInt("account_id"), rs.getString("username"),
                        rs.getString("password"));
                return Account;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}