package entertainment.chatsocketapp;

import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    private static final String LOG_TAG = SocketServer.class.getSimpleName();
    private final Handler handler = new Handler();
    private boolean flag = true;

    public void startServerSocket(final ChatMessageInterface chatMessageInterface) {

        Thread thread = new Thread(new Runnable() {
            private String stringData = null;

            @Override
            public void run() {
                try {
                    ServerSocket ss = new ServerSocket(9002);
                    while (flag) {
                        //Server is waiting for client here, if needed
                        Socket s = ss.accept();
                        Log.i(LOG_TAG, "server s, " + s);
                        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        PrintWriter output = new PrintWriter(s.getOutputStream());

                        stringData = input.readLine();
                        Log.i(LOG_TAG, "string received by server, " + stringData);
                        output.println("FROM SERVER - " + stringData.toUpperCase());
                        output.flush();

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        updateUI(stringData, chatMessageInterface);
                        if (stringData.equalsIgnoreCase("STOP")) {
                            flag = false;
                            output.close();
                            s.close();
                            break;
                        }

                        output.close();
                        s.close();
                    }
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        thread.start();
    }

    private void updateUI(final String stringData, final ChatMessageInterface chatMessageInterface) {


        handler.post(new Runnable() {
            @Override
            public void run() {

                if (!stringData.isEmpty()) {
                    chatMessageInterface.receiveMessage(stringData);
                }

            }
        });
    }

   /* @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_start_receiving:

                startServerSocket();
                buttonStartReceiving.setEnabled(false);
                buttonStopReceiving.setEnabled(true);
                break;

            case R.id.btn_stop_receiving:

                //stopping server socket logic you can add yourself
                buttonStartReceiving.setEnabled(true);
                buttonStopReceiving.setEnabled(false);
                break;
        }
    }*/
}

