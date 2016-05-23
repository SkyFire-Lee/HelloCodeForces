package io.github.skyfire_lee.hellocodeforces.ratingAction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.skyfire_lee.hellocodeforces.R;
import io.github.skyfire_lee.hellocodeforces.bean.itemBean;
import io.github.skyfire_lee.hellocodeforces.bean.rankBean;

/**
 * Created by SkyFire on 2016/5/20.
 */
public class rankAdapter extends BaseAdapter {
    private List<rankBean>list = new ArrayList<>();

    private LayoutInflater mLayoutInflater;

    public rankAdapter(Context context, List<rankBean> list) {
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
            view = mLayoutInflater.inflate(R.layout.item_rank, null);
            holder.contestName = (TextView) view.findViewById(R.id.tv_contestName);
            holder.oldRating = (TextView) view.findViewById(R.id.tv_oldRating);
            holder.newRating = (TextView) view.findViewById(R.id.tv_newRating);
            holder.Rank = (TextView) view.findViewById(R.id.tv_Rank);
            holder.body = (LinearLayout) view.findViewById(R.id.ly_body);
            view.setTag(holder);
        }
        else
        {
            holder = (MyViewHolder) view.getTag();
        }

        rankBean bean = list.get(i);

        holder.Rank.setText(bean.getRank());
        holder.newRating.setText(bean.getNewRating());
        holder.contestName.setText(bean.getContestName());
        holder.oldRating.setText(bean.getOldRating());

        if(Integer.parseInt(bean.getOldRating()) > Integer.parseInt(bean.getNewRating()))
        {
            holder.body.setBackgroundResource(R.color.ratingDown);
        }
        else
        {
            holder.body.setBackgroundResource(R.color.ratingUp);
        }
        return view;
    }

    class MyViewHolder{
        public TextView contestName;
        public TextView oldRating;
        public TextView newRating;
        public TextView Rank;
        public LinearLayout body;
    }
}
