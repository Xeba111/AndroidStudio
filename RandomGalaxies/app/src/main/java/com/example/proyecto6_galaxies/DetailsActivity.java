package com.example.proyecto6_galaxies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {

    private static Map<String, Bitmap> bitmapsGalaxies = SingletonGalaxies.getInstance().bitmaps;
    private Map<String, Galaxy> mapOfGalaxies= SingletonGalaxies.getInstance().galaxyMap;
    private ImageView galaxyImage;
    private TextView galaxyTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        galaxyImage = (ImageView) findViewById(R.id.galaxyImageLarge);
        galaxyTitle = (TextView) findViewById(R.id.galaxyTitleLarge);
        Intent intent = getIntent();
        String id  = intent.getStringExtra("id");
        galaxyTitle.setText(mapOfGalaxies.get(id).title);

        if (bitmapsGalaxies.containsKey(mapOfGalaxies.get(id).lIcon)){
            galaxyImage.setImageBitmap(bitmapsGalaxies.get(mapOfGalaxies.get(id).lIcon));
        }else {
            new LoadGalaxyImageTask(galaxyImage).execute(mapOfGalaxies.get(id).lIcon);
        }


    }


    public class LoadGalaxyImageTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView image;
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
                    bitmapsGalaxies.put(params[0], bitmap);
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