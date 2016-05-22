package io.github.skyfire_lee.hellocodeforces.contestAction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import io.github.skyfire_lee.hellocodeforces.R;
import io.github.skyfire_lee.hellocodeforces.bean.contestBean;
import io.github.skyfire_lee.hellocodeforces.bean.rankBean;

/**
 * Created by SkyFire on 2016/5/21.
 */
public class contestAdapter extends BaseAdapter{
    private List<contestBean> list;

    private LayoutInflater mLayoutInflater;

    public contestAdapter(Context context, List<contestBean> list) {
        this.list = list;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        MyViewHolder holder = null;
        if(view == null)
        {
            holder = new MyViewHolder();
            view = mLayoutInflater.inflate(R.layout.item_contest, null);
            holder.contestName = (TextView) view.findViewById(R.id.tv_contestName);
            holder.startTimeSeconds = (TextView) view.findViewById(R.id.tv_startTimeSeconds);
            holder.Phase = (TextView) view.findViewById(R.id.tv_Phase);
            holder.durationSeconds = (TextView) view.findViewById(R.id.tv_durationSeconds);
            view.setTag(holder);
        }
        else
        {
            holder = (MyViewHolder) view.getTag();
        }

        contestBean bean = list.get(i);

        holder.contestName.setText(bean.getName());
        holder.startTimeSeconds.setText(bean.getStartTimeSeconds());
        holder.Phase.setText(bean.getPhase());
        holder.durationSeconds.setText(bean.getDurationSeconds());
        return view;
    }

    class MyViewHolder{
        public TextView contestName;
        public TextView startTimeSeconds;
        public TextView Phase;
        public TextView durationSeconds;
    }
}
