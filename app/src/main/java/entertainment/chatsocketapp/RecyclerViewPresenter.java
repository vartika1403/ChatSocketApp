package entertainment.chatsocketapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import entertainment.chatsocketapp.adapter.MessageAdapter;
import entertainment.chatsocketapp.model.ChatUser;

public class RecyclerViewPresenter implements RecyclerViewPresenterInterface {
    private List<ChatUser> chatUsersList;
    private MessageAdapter messageAdapter;
    private ChatMessageInterface chatMessageInterface;


    public RecyclerViewPresenter(ChatMessageInterface chatMessageInterface, RecyclerView recyclerView) {
        this.chatMessageInterface = chatMessageInterface;
        chatUsersList = new ArrayList<ChatUser>();
        messageAdapter = new MessageAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) chatMessageInterface,
                OrientationHelper.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(messageAdapter);

    }

    @Override
    public void connectToSocketServer(ChatActivity chatActivity, String message) {
        ConnectToSocketServer connectToSocketServer = new ConnectToSocketServer(chatActivity);
        connectToSocketServer.sendMessageToSocketServer(message);
    }

    @Override
    public void sendMessageToAdapter(String message, boolean isSelf) {
     ChatUser chatUser = new ChatUser("me", message, true);
     chatUsersList.add(chatUser);
     messageAdapter.notifyDataSetChanged();
    }

    @Override
    public void startServer(){
        SocketServer socketServer = new SocketServer();
        socketServer.startServerSocket();
    }

    @Override
    public void onBindViewHolderAtPosition(ViewHolderViewInterface messageViewHolder, int position) {
        ChatUser chatUser = chatUsersList.get(position);
        if (chatUser.isSelf()) {
          messageViewHolder.setToUserName(chatUser.getUserName());
          messageViewHolder.setToMessage(chatUser.getMessage());
        } else {
            messageViewHolder.setFromUserName(chatUser.getUserName());
            messageViewHolder.setFromMessage(chatUser.getMessage());
        }

    }

    @Override
    public int getListItemCount() {
        return  chatUsersList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderForView(@NonNull ViewGroup parent, int viewType) {
      if (viewType == 1) {
          View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_message_right,
                  parent, false);
          return  new MessageToViewHolder(view);
      } else {
          if (viewType == 0) {
              View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_message_left,
                      parent, false);
              return new MessageFromViewHolder(view);
          }
      }
      return null;
    }

    @Override
    public int getItemViewTypeForItem(int position) {
       if (chatUsersList.get(position).isSelf()) {
           return 1;
       } else {
           return 0;
       }
    }
}
