package org.example;

import java.util.List;

public class MessageService {


    private MessageDAO messageDAO;
    private AccountDAO accountDAO;

    /**
     * no-args constructor for messageService which creates a MessageDAO.
     */
    public MessageService(){
        messageDAO = new MessageDAO();;
        accountDAO = new AccountDAO();
    }

    /**
     * Constructor for a messageService when a MessageDAO is provided
     * THis allows the testing of MessageService separately from MessageService
     * @param messageDAO
     */



    /**
     * the messageDAO used to persist a message to the database
     * Method should check if the message_id already exists before it  attempt to insert
     * @param message a message object
     * @return message if it was successfully inserted, null if not
     */

    public Message addMessages(Message message){

        if(isTextValid(message.getMessage_text()) && accountDAO.isValidAccount(message.getPosted_by())) {
           Message newMessage =  messageDAO.insertMessage(message);
            return newMessage;
        }
        else {
            return null;
        }
    }

    /**
     * use the messageDAO to retrieve all messages by message_id
     * @return all messages
     */
    public Message getMessagesById(int message_id) {
        return messageDAO.getMessageByID(message_id);

    }



    /**
     * Retrieve all messages
     * @return
     */
    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }


    /**
     * delete message by its message_id
     * @parammessage_id
     * @parammessage
     * @return
     */


    public Message deleteMessage(int message_id){
        if(messageDAO.getMessageByID(message_id) == null){
            return null;
        }
        else {
            Message deletedMessage = messageDAO.getMessageByID(message_id);
            messageDAO.deleteMessage(message_id);
            return deletedMessage;
        }

    }



    public Message updateMessage(int message_id, Message message) {

        if (messageDAO.getMessageByID(message_id) != null && isTextValid(message.getMessage_text())) {
            messageDAO.updateMessage(message_id, message);
            return messageDAO.getMessageByID(message_id);
        } else {
            return null;
        }
    }

    public List<Message> getMessageByAccountId(int posted_by){

        return messageDAO.getMessageUserID(posted_by);

    }


    public boolean isTextValid(String message_text){
        if(message_text.length() >= 255 || message_text == ""){
            return false;
        }
        return true;
    }



}
