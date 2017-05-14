package me.grzesik.localmessages;

import android.provider.ContactsContract;

import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.io.File;

public class Api {

    private DiskBasedCache cache;
    private RequestQueue mRequestQueue;
    static Api instance;

    private Api() {
        this.cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());

        this.mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
    }

    public static Api getInstance() {
        if (Api.instance == null) {
            Api.instance = new Api();
        }

        return Api.instance;
    }

    public void getResponse(final GetResponseCallback callback) {

        String url = "http://10.0.2.2:8080/hello-world";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                });

        // Access the RequestQueue through your singleton class.
        this.mRequestQueue.add(jsObjRequest);
    }

    public File getCacheDir() {
        return new File("tmp");
    }
}
