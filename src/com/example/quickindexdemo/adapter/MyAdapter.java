package com.example.quickindexdemo.adapter;

import java.util.ArrayList;

import com.example.quickindexdemo.R;
import com.example.quickindexdemo.bean.Friend;
import com.example.quickindexdemo.utils.PinYinUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Friend> list;

	public MyAdapter(Context context, ArrayList<Friend> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.adapter_friend, null);
		}
		ViewHolder holder = ViewHolder.getHolder(convertView);
		//设置数据
		Friend friend=list.get(position);
		holder.name.setText(friend.getName());
		
		
		String currentWord=friend.getPinyin().charAt(0)+"";
		if (position>0) {
			//获取上一个item的首字母
			String lastWord=list.get(position-1).getPinyin().charAt(0)+"";
			//拿当前的首字母和上一个首字母比较
			if (currentWord.equals(lastWord)) {//首字母相同，隐藏当前item的first_word
				holder.first_word.setVisibility(View.GONE);
			}else{
				//不一样，需要显示当前的首字母
				//由于布局是复用的，所以需要显示的时候，再次将first_word设置为可见
				holder.first_word.setVisibility(View.VISIBLE);
				holder.first_word.setText(currentWord);
			}
		}else{
			holder.first_word.setVisibility(View.VISIBLE);
			holder.first_word.setText(currentWord);
		}

		return convertView;
	}

	static class ViewHolder {
		TextView first_word;
		TextView name;

		public ViewHolder(View convertView) {
			name = (TextView) convertView.findViewById(R.id.name);
			first_word = (TextView) convertView.findViewById(R.id.first_word);
		}

		public static ViewHolder getHolder(View convertView) {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			if (holder == null) {
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			return holder;
		}
	}

}
