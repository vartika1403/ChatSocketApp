package entertainment.chatsocketapp;

public class JoinGroupChatPresenter implements  JoinGroupChatPresenterInterface {
    private MainActivity mainActivity;

    public JoinGroupChatPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onJoinGroupChat() {
    mainActivity.navigateToChatScreen();
    }
}
