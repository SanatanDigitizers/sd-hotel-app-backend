package com.sanatandigitizers.plustworoomsadmin.model;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sanatandigitizers.plustworoomsadmin.R;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.MyViewHolder> {


    Context context;
    private List<User> userList;
    public UserRecyclerAdapter(Context cntext,List<User>userList){

        this.context=cntext;
        this.userList=userList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView profileImageIv;
        private TextView userNameTv,userEmailTv,userPhoneTv;

        public MyViewHolder(View itemView){
            super(itemView);
            profileImageIv=(CircleImageView)itemView.findViewById(R.id.profile_img);
            userEmailTv=(TextView)itemView.findViewById(R.id.useremail_tv);
            userNameTv=(TextView)itemView.findViewById(R.id.tv_username);
            userPhoneTv=(TextView)itemView.findViewById(R.id.userephone_tv);

        }
    }

    @NonNull
    @Override
    public UserRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_row_user_list, parent, false);
        return new UserRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


}
