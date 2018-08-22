package com.easychange.admin.easychangemerchant.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.bean.MingxiBean;
import com.easychange.admin.easychangemerchant.utils.MyUtils;

import java.util.List;

/**
 * admin  2018/8/16 wan
 */
public class MingxiAdapter extends RecyclerView.Adapter {

    private List<MingxiBean> datas;

    private Context context;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public MingxiAdapter(List<MingxiBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mingxi_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.tv_faxing_num.setText(datas.get(position).getAmount()+"");
            viewHolder.tv_faxing_time.setText(MyUtils.getDateTimeFromMillisecond(datas.get(position).getCreateDate())+"");
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

        TextView tv_faxing_num;
        TextView tv_faxing_time;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tv_faxing_num = itemView.findViewById(R.id.tv_faxing_num);
            tv_faxing_time = itemView.findViewById(R.id.tv_faxing_time);

        }
    }

    public interface OnItemClickListener{
        void onItemClickListener(int position);
    }
}
