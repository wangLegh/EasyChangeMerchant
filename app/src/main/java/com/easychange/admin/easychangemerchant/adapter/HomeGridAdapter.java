package com.easychange.admin.easychangemerchant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.bean.HomeGridBean;

import java.util.List;

/**
 * admin  2018/8/16 wan
 */
public class HomeGridAdapter extends BaseAdapter {
    private List<HomeGridBean> lists;

    private Context context;

    public HomeGridAdapter(List<HomeGridBean> stringList, Context context) {
        this.lists = stringList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_view, null);
            vh = new ViewHolder();
            vh.icon = convertView.findViewById(R.id.item_home_view_icon);
            vh.title = convertView.findViewById(R.id.item_home_view_title);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.title.setText(lists.get(position).title);
        vh.icon.setImageDrawable(context.getResources().getDrawable(lists.get(position).icon));

        return convertView;
    }

    class ViewHolder {
        ImageView icon;

        TextView title;
    }
}
