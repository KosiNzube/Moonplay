package com.mobile.app.moonplay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchableAdapter extends BaseAdapter implements Filterable {

    Context context;
    private List<Movie> originalData = null;
    private List<Movie>filteredData = null;
    private LayoutInflater mInflater;
    private ItemFilter mFilter = new ItemFilter();

    public SearchableAdapter(Context context, List<Movie> data) {
        this.filteredData = data ;
        this.originalData = data ;
        this.context= context;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return filteredData.size();
    }

    public Object getItem(int position) {
        return filteredData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // A ViewHolder keeps references to children views to avoid unnecessary calls
        // to findViewById() on each row.
        ViewHolder holder;

        // When convertView is not null, we can reuse it directly, there is no need
        // to reinflate it. We only inflate a new View when the convertView supplied
        // by ListView is null.
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.rexo, null);


            // Creates a ViewHolder and store references to the two children views
            // we want to bind data to.
            holder = new ViewHolder();


            holder.img_item = (ImageView) convertView.findViewById(R.id.itemImage);

            holder.genre=convertView.findViewById(R.id.type);

            holder.des=convertView.findViewById(R.id.description);

            holder.txt_item_title=convertView.findViewById(R.id.tvTitle);

            holder.card=convertView.findViewById(R.id.card);



            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, comments.class);
                    intent.putExtra("image", filteredData.get(position).getImage());
                    intent.putExtra("genre",filteredData.get(position).getGenre());
                    intent.putExtra("name",filteredData.get(position).getName());
                    intent.putExtra("video",filteredData.get(position).getVideo());
                    intent.putExtra("dex",filteredData.get(position).getDescription());
                    intent.putExtra("mb",filteredData.get(position).getMb());
                    intent.putExtra("type",filteredData.get(position).getType());
                    intent.putExtra("res",filteredData.get(position).getResolution());
                    intent.putExtra("upl",filteredData.get(position).getUploader());
                    context.startActivity(intent);
                }
            });

            holder.card.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {


                    return true;
                }
            });




           // holder.text = (TextView) convertView.findViewById(R.id.list_view);

            // Bind the data efficiently with the holder.

            convertView.setTag(holder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
        }

        // If weren't re-ordering this you could rely on what you set last time
        holder.txt_item_title.setText(filteredData.get(position).getName());

        return convertView;
    }

    static class ViewHolder {

        TextView txt_item_title,genre,des,watch;
        ImageView img_item,io;
        RelativeLayout card;



    }

    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Movie> list = originalData;

            int count = list.size();
            final ArrayList<Movie> nlist = new ArrayList<Movie>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                Movie b = list.get(i);
                if (b.getName().toLowerCase().contains(filterString)) {
                    nlist.add(b);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<Movie>) results.values;
            notifyDataSetChanged();
        }

    }
}