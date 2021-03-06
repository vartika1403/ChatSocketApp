package entertainment.chatsocketapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public interface RecyclerViewPresenterInterface {
    void connectToSocketServer(ChatActivity chatActivity, String message);
    void sendMessageToAdapter(String message, boolean isSelf);
    void onBindViewHolderAtPosition(ViewHolderViewInterface messageViewHolder, int position);
    int getListItemCount();
    RecyclerView.ViewHolder onCreateViewHolderForView(@NonNull ViewGroup parent, int viewType);
    int getItemViewTypeForItem(int position);
    void startServer();
}
