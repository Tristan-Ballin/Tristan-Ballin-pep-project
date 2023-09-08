package Controller;

import Model.Account;
import Service.AccountService;
import Model.Message;
import Service.MessageService;

import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    public SocialMediaController(){
        accountService = new AccountService();
        messageService = new MessageService();
    }


    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postRegister);
        app.post("/login", this::postLogin);
        app.post("/messages", this::postMessage);
        app.get("/messages", this::getAllMessages);
        app.get("/messages/{message_id}", this::getMessageById);
        app.delete("/messages/{message_id}", this::deleteMessageById);
        app.patch("/messages/{message_id}", this::patchMessageById);
        app.get("/accounts/{account_id}/messages", this::getMessagesByUserId);

        return app;
    }

    /*
     
     */
    private void postRegister(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account Account = mapper.readValue(context.body(), Account.class);
        Account addedAccount = accountService.addAccount(Account);
        System.out.println(addedAccount);
        if(addedAccount==null){
            context.status(400);
        }else{
            context.json(mapper.writeValueAsString(addedAccount));
        }
    }

    
    private void postLogin(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account Account = mapper.readValue(context.body(), Account.class);
        Account loginAccount = accountService.getAccountByUserAccount(Account);
        if(loginAccount==null){
            context.status(401);
        }else{
            context.json(mapper.writeValueAsString(loginAccount));
        }
    }

    
    private void postMessage(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message Message = mapper.readValue(context.body(), Message.class);
        Message addedMessage = messageService.addMessage(Message);
        System.out.println("added Message "+addedMessage);
        if(addedMessage==null){
            context.status(400);
        }else{
            context.json(mapper.writeValueAsString(addedMessage));
        }
    }

    
    private void getAllMessages(Context context) throws JsonProcessingException{
    }

    
    private void getMessageById(Context context) throws JsonProcessingException{
    }

    
    private void deleteMessageById(Context context) throws JsonProcessingException{
    }

    
    private void patchMessageById(Context context) throws JsonProcessingException{
    }

    
    private void getMessagesByUserId(Context context) throws JsonProcessingException{
    }

}