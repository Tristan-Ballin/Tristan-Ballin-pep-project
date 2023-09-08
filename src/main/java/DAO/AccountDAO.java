package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class AccountDAO {


    /**
     * TODO: Retrieve a specific Account using its Username.
     *
     * You only need to change the sql String and set preparedStatement parameters.
     *
     * Remember that the format of a select where statement written as a Java String looks something like this:
     * String sql = "select * from TableName where ColumnName = ?";
     * The question marks will be filled in by the preparedStatement setString, setInt, etc methods. they follow
     * this format, where the first argument identifies the question mark to be filled (left to right, starting
     * from zero) and the second argument identifies the value to be used:
     * preparedStatement.setInt(1,int1);
     *
     * @param id a Account ID.
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
     * TODO: Update the Account identified by the Account id to the values contained in the Account object.
     *
     * You only need to change the sql String and set preparedStatement parameters.
     *
     * Remember that the format of an update PreparedStatement written as a Java String looks something like this:
     * String sql = "update TableName set ColumnName1=?, ColumnName2=? where ColumnName3 = ?;";
     * The question marks will be filled in by the preparedStatement setString, setInt, etc methods. they follow
     * this format, where the first argument identifies the question mark to be filled (left to right, starting
     * from zero) and the second argument identifies the value to be used:
     * preparedStatement.setString(1,string1);
     * preparedStatement.setString(2,string2);
     * preparedStatement.setInt(3,int1);
     *
     * @param id a Account ID.
     * @param Account a Account object. the Account object does not contain a Account ID.
     */
    public void updateAccount(int id, Account Account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "UPDATE Account SET departure_city=?, arrival_city=? where Account_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write PreparedStatement setString and setInt methods here.
            /*preparedStatement.setString(1,Account.departure_city);
            preparedStatement.setString(2,Account.arrival_city);
            preparedStatement.setInt(3,id);*/


            preparedStatement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * TODO: Retrieve all Accounts following a particular Account path.
     *
     * you only need to change the sql string and set preparedStatement parameters.
     *
     * Remember that the format of a select where statement written as a Java String looks something like this:
     * "select * from TableName where ColumnName1 = ? and ColumnName2 = ?;";
     * The question marks will be filled in by the preparedStatement setString, setInt, etc methods. they follow
     * this format, where the first argument identifies the question mark to be filled (left to right, starting
     * from zero) and the second argument identifies the value to be used:
     * preparedStatement.setString(1,"column 1 value");
     * preparedStatement.setString(2,"column 2 value");
     *
     * @param departure_city the departing city.
     * @param arrival_city the arriving city.
     * @return all Accounts from departure_city to arrival_city.
     */
    public List<Account> getAllAccountsFromCityToCity(String departure_city, String arrival_city){
        Connection connection = ConnectionUtil.getConnection();
        List<Account> Accounts = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM Account WHERE departure_city = ? and arrival_city = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write PreparedStatement setString and setInt methods here.
            preparedStatement.setString(1,departure_city);
            preparedStatement.setString(2,arrival_city);


            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                /*Account Account = new Account(rs.getInt("Account_id"), rs.getString("departure_city"),
                        rs.getString("arrival_city"));
                Accounts.add(Account);*/
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return Accounts;
    }
}