package com.sdkj.heaterbluetooth.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.sdkj.heaterbluetooth.R;

import java.util.ArrayList;
import java.util.List;

public class BottomDialogView extends LinearLayout {
    private ListView lv_content;
    private TextView btn_cancel;
    private List<String> models = new ArrayList<>();
    private ShareAdapter adapter;

    public BottomDialogView(Context context) {
        super(context);
        init();
    }

    public BottomDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomDialogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.dialog_bottom_view, this, true);
        lv_content = findViewById(R.id.rv_content);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClickCancel(view);
            }
        });
        adapter = new ShareAdapter(models);
        lv_content.setAdapter(adapter);
    }


    public void setModels(List<String> models) {
        adapter.setModels(models);
    }


    public class ShareAdapter extends BaseAdapter {
        private List<String> models;
        private ViewHodler hodler;

        public ShareAdapter(List<String> models) {
            this.models = models;
        }

        public void setModels(List<String> models) {
            this.models = models;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return models.size();
        }

        @Override
        public Object getItem(int i) {
            return models.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(getContext(), R.layout.dialog_bottom_item, null);
                hodler = new ViewHodler(view);
                view.setTag(hodler);
            } else {
                hodler = (ViewHodler) view.getTag();
            }
            final String model = models.get(i);
            hodler.tv_name.setText(model);
            hodler.ll_share.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onClickItem(i);
                    }
                }
            });
            return view;
        }

        class ViewHodler extends RecyclerView.ViewHolder {
            private LinearLayout ll_share;
            private TextView tv_name;

            public ViewHodler(View itemView) {
                super(itemView);
                ll_share = itemView.findViewById(R.id.ll_share);
                tv_name = itemView.findViewById(R.id.tv_name);
            }
        }
    }


    public interface ClickListener {
        void onClickItem(int pos);

        void onClickCancel(View v);
    }
}
