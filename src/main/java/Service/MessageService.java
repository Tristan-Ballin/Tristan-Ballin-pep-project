package Service;

import Model.Message;
import DAO.MessageDAO;
import DAO.AccountDAO;

import java.util.List;

public class MessageService {
    MessageDAO MessageDAO;
    AccountDAO AccountDAO;

    /**
     * No-args constructor for a MessageService instantiates a plain MessageDAO.
     * There is no need to modify this constructor.
     */
    public MessageService(){
        MessageDAO = new MessageDAO();
        AccountDAO = new AccountDAO();
    }

    /**
     * Constructor for a MessageService when a MessageDAO is provided.
     * This is used for when a mock MessageDAO that exhibits mock behavior is used in the test cases.
     * This would allow the testing of MessageService independently of MessageDAO.
     * There is no need to modify this constructor.
     * @param MessageDAO
     */
    public MessageService(MessageDAO MessageDAO){
        this.MessageDAO = MessageDAO;
    }

    /**
     * TODO: Use the MessageDAO to add a new Message to the database.
     */
    public Message addMessage(Message Message){
        System.out.println(Message);
        System.out.println(AccountDAO.getAccountById(Message.posted_by));
        if (Message.message_text=="" || Message.message_text.length()>254 || AccountDAO.getAccountById(Message.posted_by)==null) {
            return null;
        }

        return MessageDAO.insertMessage(Message);
    }

    /**
     * TODO: Use the MessageDAO to update an existing Message from the database.
     */
    public Message updateMessage(int Message_id, Message Message){
        if (Message.message_text=="" || Message.message_text.length()>254 || MessageDAO.getMessageById(Message_id)==null) {
            return null;
        }
        MessageDAO.updateMessage(Message_id, Message);
        return MessageDAO.getMessageById(Message_id);
    }

    /**
     * TODO: Use the MessageDAO to retrieve a List containing all Messages.
     */
    public List<Message> getAllMessages() {
        return MessageDAO.getAllMessages();
    }
    /**
     * TODO: Use the MessageDAO to retrieve a List containing all Messages.
     */
    public List<Message> getAllMessagesByUserId(int posted_by) {
        System.out.println(MessageDAO.getAllMessagesByUserId(posted_by));
        return MessageDAO.getAllMessagesByUserId(posted_by);
    }

    /**
     * TODO: Use the MessageDAO to retrieve a List containing all Messages departing from a certain city and arriving at
     */
    public Message getMessageById(int message_id) {
        if (MessageDAO.getMessageById(message_id)==null) {
            return null;
        }
        return MessageDAO.getMessageById(message_id);
    }

    /**
     * TODO: Use the MessageDAO to retrieve a List containing all Messages departing from a certain city and arriving at
     */
    public Message deleteMessageById(int message_id) {
        if (MessageDAO.getMessageById(message_id)==null) {
            return null;
        }else{
            Message temp = MessageDAO.getMessageById(message_id);
            MessageDAO.deleteMessageById(message_id);
            return temp;
        }

    }
}