package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Author: Mahlet Drar
 * Date: January 30, 2023
 *
 * MessageDAO is used for insert, and retrieve messages into/form database
 */
public class MessageDAO {

    public MessageDAO() {

    }

    /**
     * Retrieve all messages from the Message table
     *
     * @return all messages
     */


    /**
     * Insert new message into a Message database
     *
     * @param message
     */

    public Message insertMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();


        try {

            String sql = " INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?,?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());


            preparedStatement.executeUpdate();
            ResultSet pky = preparedStatement.getGeneratedKeys();
            while (pky.next()) {
                int generated_message_id = pky.getInt(1);
                 return new Message (generated_message_id, message.getPosted_by(),
                        message.getMessage_text(), message.getTime_posted_epoch());
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        // if insert statement is not successful, return null
        return null;
    }


    public List<Message> getAllMessages() {

        List<Message> messages = new ArrayList<>();

        Connection connection = ConnectionUtil.getConnection();
        try {
            /**
             *  SQL statement to retrieve all messages
             */

            String sql = "SELECT * FROM message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }

    public Message getMessageByID(int message_id) {

        Connection connection = ConnectionUtil.getConnection();

        try {
            /**
             *  SQL statement to retrieve all messages
             */

            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message_id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Message newMessage =  new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                return  newMessage;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteMessage(int message_id){


        Connection connection = ConnectionUtil.getConnection();

        try {

            String sql = "DELETE FROM message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message_id);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }


    public Message updateMessage(int message_id, Message message) {

        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = " UPDATE message SET message_text = ? WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, message.getMessage_text());
            preparedStatement.setInt(2, message_id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return message;

    }




    public List<Message> getMessageUserID(int posted_by) {

        List<Message> messages = new ArrayList<>();

        Connection connection = ConnectionUtil.getConnection();

        try {
            /**
             * SQL statement to retrieve all messages filtered by message_id
             */

            String sql = "SELECT * FROM message WHERE posted_by = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, posted_by);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                Message newMessage = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                messages.add(newMessage);


            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return messages;

    }



}









