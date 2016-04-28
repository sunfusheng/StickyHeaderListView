package com.sunfusheng.StickyHeaderListView.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.model.ChannelEntity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class HeaderChannelAdapter extends BaseListAdapter<ChannelEntity> {

    public HeaderChannelAdapter(Context context, List<ChannelEntity> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_channel, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ChannelEntity entity = getItem(position);

        holder.tvTitle.setText(entity.getTitle());
        mImageManager.loadCircleImage(entity.getImage_url(), holder.ivImage);
        if (TextUtils.isEmpty(entity.getTips())) {
            holder.tvTips.setVisibility(View.INVISIBLE);
        } else {
            holder.tvTips.setVisibility(View.VISIBLE);
            holder.tvTips.setText(entity.getTips());
        }

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_image)
        ImageView ivImage;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_tips)
        TextView tvTips;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
