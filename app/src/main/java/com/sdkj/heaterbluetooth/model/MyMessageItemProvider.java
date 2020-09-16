package com.sdkj.heaterbluetooth.model;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.ConstanceValue;
import com.sdkj.heaterbluetooth.app.Notice;
import com.sdkj.heaterbluetooth.util.RxBus;


import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;

@ProviderTag(messageContent = MyMessage.class, showReadState = true)
public class MyMessageItemProvider extends IContainerItemProvider.MessageProvider<MyMessage> {
    private Context mContext;

    @Override
    public void bindView(View view, int i, MyMessage customMessage, UIMessage message) {
        //根据需求，适配数据
        ViewHolder holder = (ViewHolder) view.getTag();
        if (message.getMessageDirection() == Message.MessageDirection.SEND) {//消息方向，自己发送的
            holder.message.setBackgroundResource(io.rong.imkit.R.drawable.rc_ic_bubble_right);
        } else {
            holder.message.setBackgroundResource(io.rong.imkit.R.drawable.rc_ic_bubble_left);
        }
        holder.tv1.setText("驾车:从当前位置到" + customMessage.getCustomRepairName());
        holder.tv2.setText("总公里数" + customMessage.getCustomRepairDis() + "KM");
        holder.tv3.setText(customMessage.getAddr());
        if (mContext != null) {
            Glide.with(mContext).load(customMessage.getCustomRepairUrl())
                    .error(R.mipmap.logi_icon)
                    .into(holder.iv_main);
        }
    }

    @Override
    public Spannable getContentSummary(MyMessage customMessage) {
        return new SpannableString(customMessage.getCustomTitle());
    }

    @Override
    public void onItemClick(View view, int i, MyMessage customMessage, UIMessage uiMessage) {
        Notice notice = new Notice();
        notice.type = ConstanceValue.MSG_SERVICE_CHAT;
        notice.content = customMessage;
        RxBus.getDefault().sendRx(notice);
    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        mContext = context;
        //这就是展示在会话界面的自定义的消息的布局
        View view = LayoutInflater.from(context).inflate(R.layout.a_item_service_chat, null);
        ViewHolder holder = new ViewHolder();
        holder.tv1 = view.findViewById(R.id.tv1);
        holder.tv2 = view.findViewById(R.id.tv2);
        holder.tv3 = view.findViewById(R.id.tv3);
        holder.iv_main = view.findViewById(R.id.iv_main);
        holder.message = view.findViewById(R.id.message);
        view.setTag(holder);
        return view;
    }

    private static class ViewHolder {
        TextView tv1, tv2, tv3;
        ImageView iv_main;
        View message;
    }
}
