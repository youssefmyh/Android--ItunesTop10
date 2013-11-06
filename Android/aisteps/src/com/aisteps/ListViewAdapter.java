package com.aisteps;

import java.util.ArrayList;

import com.aisteps.controller.Entry;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * ListViewApdapter is a sub class of BaseAdapter 
 * used for create a view contain song name ,Image,save Button ,Browse Button 
 * 
 * */

public class ListViewAdapter extends BaseAdapter implements OnClickListener {
	// Entries List
	ArrayList<Entry> entries;
	private Context context;

	private ArrayList<ImageView> imagesViews;

	public ListViewAdapter(Context context, ArrayList<Entry> entries) {
		this.context = context;
		this.entries = entries;
		imagesViews = new ArrayList<ImageView>();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return entries.size();
	}

	@Override
	public Object getItem(int item) {
		// TODO Auto-generated method stub
		return entries.get(item);
	}

	@Override
	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int index, View convertedView, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		View view = convertedView;
		if (convertedView == null) {
			LayoutInflater inflator = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflator.inflate(R.layout.entry, null);
		}
		TextView titleView = (TextView) view.findViewById(R.id.title);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		Button saveToDisk = (Button) view.findViewById(R.id.saveimage);
		Button browse = (Button) view.findViewById(R.id.browseimage);

		imageView.setTag(entries.get(index).getId());
		entries.get(index).setEntryImageView(imageView);
		imagesViews.add(imageView);

		titleView.setText(entries.get(index).getTitle());
		view.setTag(entries.get(index));

		browse.setTag(entries.get(index));
		saveToDisk.setTag(entries.get(index));

		view.setOnClickListener((Aisteps) context);
		return view;
	}

	public void setImageBySongID(Drawable drawable, String songId) {
		if (drawable == null)
			return;
		for (int i = 0; i < imagesViews.size(); i++) {
			if (imagesViews.get(i).getTag() != null) {
				if (imagesViews.get(i).getTag().toString()
						.equalsIgnoreCase(songId)) {
					imagesViews.get(i).setImageDrawable(drawable);
					break;
				}
			}
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		System.out.println("On Click");

	}

}
