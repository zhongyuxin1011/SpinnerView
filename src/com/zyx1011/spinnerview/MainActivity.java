package com.zyx1011.spinnerview;

import java.util.ArrayList;
import java.util.List;

import com.zyx1011.spinnerview.view.SpinnerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private SpinnerView mSv;
	private List<String> mData;
	private Sadapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSv = (SpinnerView) findViewById(R.id.show);

		initData();
		initEvent();
	}

	private void initEvent() {
		mAdapter = new Sadapter();
		mSv.setAdapter(mAdapter);
		mSv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				mSv.setText(mData.get(position));
				mSv.dismiss();
			}
		});
	}

	private void initData() {
		mData = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			mData.add("10000" + i);
		}
	}

	private class Sadapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			return mData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.listview_item, parent,
						false);
				holder.number = (TextView) convertView.findViewById(R.id.number);
				holder.delete = (ImageView) convertView.findViewById(R.id.delete);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.number.setText(mData.get(position));
			holder.delete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mData.remove(mData.get(position));
					
					if (mAdapter != null) {
						mAdapter.notifyDataSetChanged();
					}
				}
			});


			return convertView;
		}
	}

	private class ViewHolder {
		ImageView delete;
		TextView number;
	}

}
