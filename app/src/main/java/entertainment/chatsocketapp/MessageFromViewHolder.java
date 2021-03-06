package entertainment.chatsocketapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MessageFromViewHolder extends RecyclerView.ViewHolder
        implements ViewHolderViewInterface{
    private TextView userName;
    private TextView userMessage;

    public MessageFromViewHolder(View itemView) {
        super(itemView);
        userName = (TextView)itemView.findViewById(R.id.user_from_text_view);
        userMessage = (TextView)itemView.findViewById(R.id.user_from_message_text_view);
    }

    @Override
    public void setToUserName(String name) {
    }

    @Override
    public void setToMessage(String message) {

    }

    @Override
    public void setFromUserName(String name) {
        userName.setText(name);
    }

    @Override
    public void setFromMessage(String messageFrom) {
       userMessage.setText(messageFrom);
    }
}
