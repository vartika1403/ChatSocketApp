package entertainment.chatsocketapp.model;

public class ChatUser {
    String userName;
    String message;
    boolean isSelf;

    public ChatUser(String userName, String message, boolean isSelf) {
        this.userName = userName;
        this.message = message;
        this.isSelf = isSelf;
    }

    public boolean isSelf() {
        return isSelf;
    }

    public void setSelf(boolean self) {
        isSelf = self;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
