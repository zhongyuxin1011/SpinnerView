package com.zyx1011.spinnerview.view;

import com.zyx1011.spinnerview.R;
import com.zyx1011.spinnerview.utils.DisplayUtils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

public class SpinnerView extends LinearLayout implements OnClickListener {

	private EditText mEt;
	private ImageView mIv;
	private ListAdapter mAdapter;
	private PopupWindow mPopupWindow;
	private OnItemClickListener mItemClickListener;

	public SpinnerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		LayoutInflater.from(getContext()).inflate(R.layout.view_spinner, this, true);
		mEt = (EditText) findViewById(R.id.edit);
		mIv = (ImageView) findViewById(R.id.select);

		mIv.setOnClickListener(this);
	}

	public SpinnerView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SpinnerView(Context context) {
		this(context, null);
	}

	@Override
	public void onClick(View v) {
		ListView view = new ListView(getContext());
		view.setBackgroundResource(R.drawable.listview_background);

		if (mAdapter != null) {
			view.setAdapter(mAdapter);
		}

		if (mItemClickListener != null) {
			view.setOnItemClickListener(mItemClickListener);
		}

		if (mPopupWindow == null) {
			mPopupWindow = new PopupWindow(view, mEt.getWidth(), DisplayUtils.dp2px(getContext(), 300), false);
		}
		mPopupWindow.setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.showAsDropDown(mEt);
	}

	public void setAdapter(ListAdapter adapter) {
		this.mAdapter = adapter;
	}

	public void setOnItemClickListener(OnItemClickListener itemClickListener) {
		this.mItemClickListener = itemClickListener;
	}

	public void setText(String text) {
		if (text != null) {
			mEt.setText(text);
			mEt.setSelection(text.length());
		} else {
			throw new IllegalArgumentException("参数不能为空!");
		}
	}

	public void dismiss() {
		if (mPopupWindow != null) {
			mPopupWindow.dismiss();
		}
	}

}
