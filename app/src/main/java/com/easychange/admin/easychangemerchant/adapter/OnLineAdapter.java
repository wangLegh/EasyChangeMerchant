package com.easychange.admin.easychangemerchant.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;

import java.util.List;

/**
 * admin  2018/8/16 wan
 */
public class OnLineAdapter extends RecyclerView.Adapter {

    private List<String> datas;

    private Context context;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public OnLineAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_online_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClickListener();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        TextView tvActionName;
        TextView tvActionTime;
        TextView tvActionLimit;
        TextView tvSuccessNum;
        TextView tvCoinNum;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tvActionName = itemView.findViewById(R.id.item_on_line_action_name);
            tvActionTime = itemView.findViewById(R.id.item_on_line_action_time);
            tvActionLimit = itemView.findViewById(R.id.item_on_line_action_limit);
            tvSuccessNum = itemView.findViewById(R.id.item_on_line_action_successNum);
            tvCoinNum = itemView.findViewById(R.id.item_on_line_action_coinNum);
        }
    }

    public interface OnItemClickListener{
        void onItemClickListener();
    }
}
