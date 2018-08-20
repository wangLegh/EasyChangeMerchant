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
 * admin  2018/8/17 wan
 */
public class AuditActionAdapter extends RecyclerView.Adapter {

    private Context context;

    private List<ActionBean> datas;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public AuditActionAdapter(Context context, List<ActionBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_audit_action_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            viewHolder.tvActionName.setText("活动名称：" + datas.get(position).getActivityTitle());

            viewHolder.tvActionTime.setText(format.format(datas.get(position).getBeginTime()) + "-" + format.format(datas.get(position).getEndTime()));
            switch (datas.get(position).getActivityTime()) {
                case "0":
                    viewHolder.tvCouponTime.setText("工作日 每天" + datas.get(position).getTimeLimit());
                    break;
                case "1":
                    viewHolder.tvCouponTime.setText("休息日 每天" + datas.get(position).getTimeLimit());
                    break;
                case "2":
                    viewHolder.tvCouponTime.setText("全部 每天" + datas.get(position).getTimeLimit());
                    break;
            }

            viewHolder.tvCouponNum.setText(datas.get(position).getCount() + "");

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
        TextView tvCouponTime;
        TextView tvCouponNum;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tvActionName = itemView.findViewById(R.id.item_audit_action_name);
            tvActionTime = itemView.findViewById(R.id.item_audit_action_time);
            tvCouponTime = itemView.findViewById(R.id.item_audit_action_couponTime);
            tvCouponNum = itemView.findViewById(R.id.item_audit_action_couponNum);
        }
    }

    public interface OnItemClickListener{
        void onItemClickListener(ActionBean actionBean, int position);
    }
}
