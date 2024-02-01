package com.example.proyecto6_galaxies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Adapter extends ArrayAdapter<Galaxy> {

    private Map<String, Bitmap> bitmaps = new HashMap<>();
    private static class ViewHolder{
        ImageView conditionImageView;
        TextView titleTextView;
        TextView urlTextView;
    }

    public Adapter(Context context, List<Galaxy> galaxies) {
        super(context, -1, galaxies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Galaxy galaxy = getItem(position);
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item, parent,false);
            viewHolder.conditionImageView = (ImageView) convertView.findViewById(R.id.conditionImageView);
            viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
            viewHolder.urlTextView = (TextView) convertView.findViewById(R.id.urlTextView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (bitmaps.containsKey(galaxy.tIcon)){
            viewHolder.conditionImageView.setImageBitmap(bitmaps.get(galaxy.tIcon));
        }else {
            new LoadGalaxyImageTask(viewHolder.conditionImageView).execute(galaxy.tIcon);
        }

        viewHolder.titleTextView.setText((position+1)+".- "+galaxy.title);
        viewHolder.urlTextView.setText(galaxy.tIcon);

        return convertView;
    }

    public class LoadGalaxyImageTask extends AsyncTask<String, Void,Bitmap>{
        private  ImageView image;
        public LoadGalaxyImageTask(ImageView image){
            this.image = image;
        }


        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            HttpURLConnection connection = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                try(InputStream input = connection.getInputStream()) {
                    bitmap = BitmapFactory.decodeStream(input);
                    bitmaps.put(params[0], bitmap);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                connection.disconnect();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            image.setImageBitmap(bitmap);
        }
    }
}
