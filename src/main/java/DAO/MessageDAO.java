package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    /**
     * TODO: Retrieve all Messages from the Message table.
     *
     * @return all Messages.
     */
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> Messages = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM Message";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                
                Message Message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"),
                        rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                Messages.add(Message);
                //System.out.println(Messages);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return Messages;
    }
    /**
     * TODO: Retrieve all Messages from the Message table where posted_by=user_id.
     *
     * @return all Messages by user.
     */
    public List<Message> getAllMessagesByUserId(int id){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> Messages = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM Message WHERE posted_by = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                
                Message Message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"),
                        rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                Messages.add(Message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return Messages;
    }

    /**
     * TODO: Delete a specific Message using its Message ID.
     *
     * @param id a Message ID.
     */
    public Message deleteMessageById(int id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "DELETE FROM Message where message_id = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setInt(1,id);


        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * TODO: Retrieve a specific Message using its Message ID.
     * 
     * @param id a Message ID.
     */
    public Message getMessageById(int id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM Message where message_id = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setInt(1,id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message Message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"),
                        rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                return Message;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * TODO: Add a Message record into the database which matches the values contained in the Message object.
     *
     * @param Message an object modelling a Message. the Message object does not contain a Message ID.
     */
    public Message insertMessage(Message Message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here. When inserting, you only need to define the departure_city and arrival_city
            //values (two columns total!)
            String sql = "INSERT INTO Message (message_text, posted_by, time_posted_epoch) VALUES (?,?,?);" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setString(1,Message.message_text);
            preparedStatement.setInt(2,Message.posted_by);
            preparedStatement.setLong(3,Message.time_posted_epoch);

            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_Message_id = (int) pkeyResultSet.getLong(1);
                return new Message(generated_Message_id, Message.getPosted_by(), Message.getMessage_text(), Message.getTime_posted_epoch());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * TODO: Update the Message identified by the Message id to the values contained in the Message object.
     *
     * @param id a Message ID.
     * @param Message a Message object. the Message object does not contain a Message ID.
     */
    public void updateMessage(int id, Message Message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "UPDATE Message SET message_text=? where Message_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write PreparedStatement setString and setInt methods here.
            preparedStatement.setString(1,Message.message_text);
            preparedStatement.setInt(2,id);


            preparedStatement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

}