package com.easychange.admin.easychangemerchant.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.bean.DuihuanBean;

import java.util.List;

/**
 * admin  2018/8/16 wan
 */
public class TanjifenAdapter extends RecyclerView.Adapter {

    private List<DuihuanBean.ListBean> datas;

    private Context context;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public TanjifenAdapter(List<DuihuanBean.ListBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tanjifen_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.tv_tanjifen_duihuan.setText(datas.get(position).getSort()+datas.get(position).getName());
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

        TextView tv_tanjifen_duihuan;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tv_tanjifen_duihuan = itemView.findViewById(R.id.tv_tanjifen_duihuan);

        }
    }

    public interface OnItemClickListener{
        void onItemClickListener(int position);
    }
}
