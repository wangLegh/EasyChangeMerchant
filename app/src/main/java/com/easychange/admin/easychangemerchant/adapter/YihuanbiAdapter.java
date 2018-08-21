package com.easychange.admin.easychangemerchant.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.bean.YihuanbiBean;
import com.easychange.admin.easychangemerchant.utils.MyUtils;

import java.util.List;

/**
 * admin  2018/8/16 wan
 */
public class YihuanbiAdapter extends RecyclerView.Adapter {

    private List<YihuanbiBean.ListBean> mData;
    private Context context;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public YihuanbiAdapter(List<YihuanbiBean.ListBean> datas, Context context) {
        this.mData = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_yihuan_list, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.tv_end_time.setText(MyUtils.getDateTimeFromMillisecond(mData.get(position).getDealTime()));
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
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        TextView tv_end_time;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tv_end_time = itemView.findViewById(R.id.tv_end_time);

        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(int position);
    }
}
