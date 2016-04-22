package com.sunfusheng.StickyHeaderListView.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.manager.ImageManager;
import com.sunfusheng.StickyHeaderListView.model.TravelingEntity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class TravelingAdapter extends BaseListAdapter<TravelingEntity> {

    public TravelingAdapter(Context context) {
        super(context);
    }

    public TravelingAdapter(Context context, List<TravelingEntity> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_travel, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TravelingEntity entity = getItem(position);

        holder.tvTitle.setText(entity.getFrom() + entity.getTitle() + entity.getType());
        holder.tvRank.setText("排名：" + entity.getRank());
        ImageManager.getInstance().loadUrlImage(mContext, entity.getImage_url(), holder.ivImage);

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_image)
        ImageView ivImage;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_rank)
        TextView tvRank;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
