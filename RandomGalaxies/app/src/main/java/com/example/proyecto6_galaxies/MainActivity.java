
/*
        Proyecto 6: Descarga de imagenes en Background
        Joel del Castillo 211456
        Jose Luis Santillan 211496

*/

package com.example.proyecto6_galaxies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<Galaxy> listOfGalaxies = SingletonGalaxies.getInstance().galaxyList;
    private Map<String, Galaxy> mapOfGalaxies= SingletonGalaxies.getInstance().galaxyMap;
    private ListView galaxiesListView;
    private Adapter galaxyAdapter;
    public URL url = createURL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        galaxiesListView = (ListView) findViewById(R.id.galaxiesListView);
        galaxyAdapter = new Adapter(this, listOfGalaxies);
        galaxiesListView.setAdapter(galaxyAdapter);
        galaxiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("id", listOfGalaxies.get(position).id);
                startActivity(intent);
            }
        });

        if (url != null) {
            GetGalaxiesTask getGalaxiesTask = new GetGalaxiesTask();
            getGalaxiesTask.execute(url);
            Toast.makeText(MainActivity.this, "Downloading icons...", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, "Error en el url", Toast.LENGTH_SHORT).show();
        }
    }

    private URL createURL() {
        String baseURL = "https://jsonkeeper.com/b/JIN3";
        try {
            return new URL(baseURL);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private class GetGalaxiesTask extends AsyncTask<URL, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(URL... params) {
            HttpURLConnection connection = null;

            try {
                connection = (HttpURLConnection) params[0].openConnection();
                int response = connection.getResponseCode();

                if (response == HttpURLConnection.HTTP_OK) {
                    StringBuilder builder = new StringBuilder();

                    try (BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()))) {

                        String line;

                        while ((line = reader.readLine()) != null) {
                            builder.append(line);
                        }
                    }
                    catch (IOException e) {
                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    return new JSONObject(builder.toString());
                }
                else {
                    Toast.makeText(MainActivity.this, "Error Connection", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e) {
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            finally {
                connection.disconnect();
            }

            return null;
        }


        @Override
        protected void onPostExecute(JSONObject galaxies) {
            convertJSONtoArrayList(galaxies);
            Log.w("Json", galaxies + "");
            galaxyAdapter.notifyDataSetChanged();
            galaxiesListView.smoothScrollToPosition(0);
        }
    }


    private void convertJSONtoArrayList(JSONObject galaxies) {
        listOfGalaxies.clear();
        try {
            JSONArray list = galaxies.getJSONArray("galaxies");

            for (int i = 0; i < list.length(); ++i) {
                JSONObject galaxy = list.getJSONObject(i);
                String title = galaxy.getString("title");
                String id = galaxy.getString("id");
                listOfGalaxies.add(new Galaxy(title, id));
                mapOfGalaxies.put(id,new Galaxy(title,id));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }




}