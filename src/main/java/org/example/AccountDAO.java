package org.example;

import java.sql.*;

/**
 * Author: Mahlet Drar
 * Date: January 30, 2023
 *
 * AccountDAO is used for insert, and retrieve accounts/users into/form database
 */

public class AccountDAO {

    public AccountDAO(){

    }

    /**
     * Get all accounts with user_id & username fields.
     * Since password is confidential data, it shouldn't retrieve in this method
     * @return all Accounts
     */


    public Account getAccountByUserName(String username){

        Connection connection = ConnectionUtil.getConnection();

        try {
            //Write SQL logic to retrieve all accounts
            String sql = "SELECT * FROM Account WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"));
                return  account;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    public Account insertNewAccountUser(Account account){

        Connection connection = ConnectionUtil.getConnection();

        try {

            String sql = "INSERT INTO account (username, password) values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();

            ResultSet pky = preparedStatement.getGeneratedKeys();
            while (pky.next()){
                int generated_account_id = pky.getInt(1);
                return new Account(generated_account_id , account.getUsername(), account.getPassword());

            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());

        }

        return null;
    }


    public Account accountLogin(Account account){

        Connection connection = ConnectionUtil.getConnection();
        try {

            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            ResultSet rs = preparedStatement.executeQuery();


            while(rs.next()) {
                Account logedAccount = new Account(rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"));
                return  logedAccount;


            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;

    }

    public Account getAccountByID(int account_id){

        Connection connection = ConnectionUtil.getConnection();

        try {
            //Write SQL logic to retrieve all accounts
            String sql = "SELECT * FROM Account WHERE account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account_id);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Account accountById = new Account(rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"));
                return  accountById;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean isValidAccount(int account_id){

        if(getAccountByID(account_id) == null ){
            return false;
        }
        return true;
    }




}
