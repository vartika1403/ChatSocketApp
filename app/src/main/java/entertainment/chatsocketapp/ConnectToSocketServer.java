package entertainment.chatsocketapp;

import android.net.wifi.WifiManager;
import android.os.Handler;
import android.text.format.Formatter;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import static android.content.Context.WIFI_SERVICE;

public class ConnectToSocketServer {
    private static final String LOG_TAG = ConnectToSocketServer.class.getSimpleName();
    private ChatActivity chatActivity;
    private static final String ipReceiver = "192.168.0.101";
    private static final String ipSendor = "192.168.0.107";

    public ConnectToSocketServer(ChatActivity chatActivity) {
        this.chatActivity = chatActivity;
    }

    public void sendMessageToSocketServer(final String message) {
        // to be implemented by Rxjava/RxAndroid
        Log.i(LOG_TAG, "message send to socket server, " + message);
        final Handler handler = new Handler();
        final String ipAddress = getIpAddress();
        Log.i(LOG_TAG, "ipAddress, " + ipAddress);
        
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    //Replace below IP with the IP of that device in which server socket open.
                    //If you change port then change the port number in the server side code also.
                    InetAddress host = InetAddress.getLocalHost();
                    Log.i(LOG_TAG, "host, "  + host);
                    Log.i(LOG_TAG, "ipAddress, " + host.getHostName());
                    Socket s = new Socket(ipReceiver, 9002);
                    Log.i(LOG_TAG, "s socket, " + s);
                    OutputStream out = s.getOutputStream();

                    PrintWriter output = new PrintWriter(out);
                    output.println(message);
                    output.flush();
                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    final String st = input.readLine();
                    Log.i(LOG_TAG, "message received to server, " + st);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });

                    output.close();
                    out.close();
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    private String getIpAddress() {
        WifiManager wm = (WifiManager)chatActivity.getApplicationContext().getSystemService(WIFI_SERVICE);
        assert wm != null;
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        return  ip;
    }


}
