package com.easychange.admin.easychangemerchant.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.bean.ActionBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * admin  2018/8/16 wan
 */
public class OnLineAdapter extends RecyclerView.Adapter {

    private List<ActionBean> datas;

    private Context context;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public OnLineAdapter(List<ActionBean> datas, Context context) {
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            viewHolder.tvActionName.setText("活动名称：" + datas.get(position).getActivityTitle());

            viewHolder.tvActionTime.setText(format.format(datas.get(position).getBeginTime()) + "-" + format.format(datas.get(position).getEndTime()));

            viewHolder.tvActionLimit.setText("满" + datas.get(position).getFull() + "减" + datas.get(position).getSub());

            viewHolder.tvSuccessNum.setText(datas.get(position).getOrderQuantity() + "单");

            viewHolder.tvCoinNum.setText(datas.get(position).getGainCurrency() + "易换券");

            viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClickListener(datas.get(position), position);
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
        void onItemClickListener(ActionBean actionBean, int position);
    }
}
