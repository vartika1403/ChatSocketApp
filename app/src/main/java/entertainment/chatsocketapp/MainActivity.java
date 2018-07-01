package entertainment.chatsocketapp;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ChatViewInterface{
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private JoinGroupChatPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new JoinGroupChatPresenter(this);

        getIpAddress();
    }

    private void getIpAddress() {
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
    }

    @OnClick(R.id.join_button)
    public void joinChat() {
      presenter.onJoinGroupChat();
    }

    @Override
    public void navigateToChatScreen() {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);

    }

    @Override
    public void onEnterName() {

    }
}
