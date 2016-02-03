package com.letterlistdamo;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AlphabetIndexer;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by JiaJi on 2015/12/4.
 */
public class OrderAdapter extends BaseAdapter
{
    private Cursor cursor;
    private LayoutInflater inflater;
    private AlphabetIndexer indexer;
    public OrderAdapter(Context context, Cursor cursor, AlphabetIndexer indexer)
    {
        this.cursor = cursor;
        inflater = LayoutInflater.from(context);
        this.indexer = indexer;

    }
    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Cursor getItem(int position) {
        cursor.moveToPosition(position);
        return cursor;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.item_express, parent, false);
            holder = new ViewHolder();
            holder.tvLetter = (TextView) convertView.findViewById(R.id.tvLetter_item_express);
            holder.tvCompanyName = (TextView) convertView.findViewById(R.id.tvCompanyName_item_express);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        cursor.moveToPosition(position);
        holder.tvCompanyName.setText(cursor.getString(cursor.getColumnIndex(ExpressDbHelper.TABLE_COMPANY_COMPANY_NAME)));
        //获取这个位置代表的字符在字符表中的位置
        int section = indexer.getSectionForPosition(position);
        //判断当前位置是否是第一个出现这个字符，indexer.getPositionForSection(section)获取第一次出现的位置
        if (position == indexer.getPositionForSection(section))
        {
            holder.tvLetter.setVisibility(View.VISIBLE);
            holder.tvLetter.setText(cursor.getString(cursor.getColumnIndex(ExpressDbHelper.TABLE_COMPANY_COMPANY_INITIAL)));
        }
        else
        {
            holder.tvLetter.setVisibility(View.GONE);
        }
        return convertView;
    }
    private class ViewHolder
    {
        TextView tvLetter;
        TextView tvCompanyName;
    }
}
