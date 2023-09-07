package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
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

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postRegister(Context context) {
        context.json("sample text");
    }

    
    private void postLogin(Context context) {
        context.json("sample text");
    }

    
    private void postMessage(Context context) {
        context.json("sample text");
    }

    
    private void getAllMessages(Context context) {
        context.json("sample text");
    }

    
    private void getMessageById(Context context) {
        context.json("sample text");
    }

    
    private void deleteMessageById(Context context) {
        context.json("sample text");
    }

    
    private void patchMessageById(Context context) {
        context.json("sample text");
    }

    
    private void getMessagesByUserId(Context context) {
        context.json("sample text");
    }

}