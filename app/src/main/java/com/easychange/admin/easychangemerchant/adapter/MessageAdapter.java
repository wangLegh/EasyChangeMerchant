package com.easychange.admin.easychangemerchant.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.bean.MessageBean;
import com.easychange.admin.easychangemerchant.utils.MyUtils;

import java.util.List;

/**
 * admin  2018/8/16 wan
 */
public class MessageAdapter extends RecyclerView.Adapter {

    private List<MessageBean> datas;

    private Context context;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public MessageAdapter(List<MessageBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    public void insertData(List<MessageBean> data){
        this.datas.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.tvMessageContent.setText(datas.get(position).getTitle());
            String dateTimeFromMillisecond = MyUtils.getDateTimeFromMillisecond(datas.get(position).getCreateDate());
            viewHolder.tvMessageTime.setText(dateTimeFromMillisecond);
            viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClickListener(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        TextView tvMessageContent;
        TextView tvMessageTime;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tvMessageContent = itemView.findViewById(R.id.tv_message_content);
            tvMessageTime = itemView.findViewById(R.id.tv_message_time);

        }
    }

    public interface OnItemClickListener{
        void onItemClickListener(int position);
    }
}
