package io.github.skyfire_lee.hellocodeforces.blogAction;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.github.skyfire_lee.hellocodeforces.R;
import io.github.skyfire_lee.hellocodeforces.bean.blogBean;

/**
 * Created by SkyFire on 2016/5/21.
 */
public class blogAdapter extends BaseAdapter {
    private List<blogBean> list;

    private LayoutInflater mLayoutInflater;

    public blogAdapter(Context context, List<blogBean> list) {
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
    public View getView(int position, View view, ViewGroup parent) {
        MyViewHolder holder = null;
        if(view == null)
        {
            holder = new MyViewHolder();
            view = mLayoutInflater.inflate(R.layout.item_blog, null);
            holder.title = (TextView) view.findViewById(R.id.tv_BlogTitle);
            holder.author = (TextView) view.findViewById(R.id.tv_author);
            holder.creationTimeSeconds = (TextView) view.findViewById(R.id.tv_creationTimeSeconds);
            view.setTag(holder);
        }
        else
        {
            holder = (MyViewHolder) view.getTag();
        }
        blogBean bean = list.get(position);

        holder.title.setText(Html.fromHtml(bean.getTitle()));
        holder.author.setText(bean.getAuthor());
        holder.creationTimeSeconds.setText(bean.getCreationTimeSeconds());

        return view;
    }

    class MyViewHolder{
        public TextView title;
        public TextView author;
        public TextView creationTimeSeconds;
    }
}
