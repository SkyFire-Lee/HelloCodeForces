package io.github.skyfire_lee.hellocodeforces.userAction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.github.skyfire_lee.hellocodeforces.R;
import io.github.skyfire_lee.hellocodeforces.bean.itemBean;

/**
 * Created by SkyFire on 2016/5/18.
 */
public class InfoAdapter extends BaseAdapter {

    private List<itemBean> list;

    private LayoutInflater mLayoutInflater;

    public InfoAdapter(Context context, List<itemBean> list) {
        mLayoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        if(view == null)
        {
            holder = new MyViewHolder();
            view = mLayoutInflater.inflate(R.layout.item_user_info, null);
            holder.key = (TextView) view.findViewById(R.id.id_key);
            holder.value = (TextView) view.findViewById(R.id.id_value);
            view.setTag(holder);
        }
        else
        {
            holder = (MyViewHolder) view.getTag();
        }

        itemBean bean = list.get(i);

        holder.key.setText(bean.getKey());
        holder.value.setText(bean.getValue());
        return view;
    }

    class MyViewHolder{
        public TextView key;
        public TextView value;
    }
}
