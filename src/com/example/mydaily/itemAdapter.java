package com.example.mydaily;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mydaily.R;
import com.example.mydaily.item;
import com.example.mydaily.itemAdapter.ViewHolder;

public class itemAdapter extends ArrayAdapter<item>{

	 private int resourceId;
	 private int num;

	    public itemAdapter(Context context, int textViewResourceId,
	            List<item> objects) {
	        super(context, textViewResourceId, objects);
	        resourceId = textViewResourceId;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        item per = getItem(position); 
	        View view;
	        ViewHolder viewHolder;
	        if(convertView==null){
	        	view= LayoutInflater.from(getContext()).inflate(resourceId, null);
	        	if(num%2==0){
	    			view.setBackgroundColor(Color.argb(0, 0, 0, 255));
	    		}else{
	    			view.setBackgroundColor(Color.argb(10, 10, 10, 255));
	    		}
	        	viewHolder=new ViewHolder();
	        	viewHolder.itemDate = (TextView) view.findViewById (R.id.item_date);
	        	viewHolder.itemContent = (TextView) view.findViewById (R.id.item_content);
	            view.setTag(viewHolder); // 将ViewHolder存储在View中
	        }else{
	        	view = convertView;
	        	viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
	        }
	        viewHolder.itemDate.setText(per.getDate());
	        viewHolder.itemContent.setText(per.getContentlimit());
	        ++num;
	        return view;
	    }
	    
	    class ViewHolder {
	        TextView itemDate;
	        TextView itemContent;

	    }
}
