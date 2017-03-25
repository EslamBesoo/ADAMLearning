package com.smaz.adamlearning;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.smaz.adamlearning.Adapter.CategoryAdapter;
import com.smaz.adamlearning.Model.Category;
import com.smaz.adamlearning.Util.AppStatus;
import com.smaz.adamlearning.Util.LocaleHelper;
import com.smaz.adamlearning.Util.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class HomeFrag extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Category> categoryArrayList = new ArrayList<>();
    private CategoryAdapter adapter;

    private OnFragmentInteractionListener mListener;

    public HomeFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            LoadJson();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
    public void LoadJson() throws MalformedURLException {

        if (AppStatus.getInstance(getActivity()).isOnline()) {
            String url = BuildConfig.JSONURL;

            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String data1 =  response.getString(LocaleHelper.getLanguage(getActivity()));
                                JSONObject data2 = new JSONObject(data1);
                                String data3 = data2.getString("categories");
                                Log.i("Data",data3);
                                JSONArray array1 = new JSONArray(data3);



                                for (int i = 0; i < array1.length(); i++) {
                                    JSONObject jsonPart = array1.getJSONObject(i);
                                    String categoryId;
                                    String categotyName;
                                    String CategoryImg;

                                    categoryId = jsonPart.getString("id");
                                    categotyName = jsonPart.getString("name");

                                    CategoryImg = jsonPart.getString("image");

                                    String url2 = BuildConfig.MAINURL;
                                    String imageurl = url2 + CategoryImg;

                                    Category category = new Category(categoryId,categotyName,imageurl);
                                    categoryArrayList.add(category);

                                }

                                adapter = new CategoryAdapter(getContext(), categoryArrayList);
                                recyclerView.setAdapter(adapter);
//                                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
//                                    @Override
//                                    public void onClick(View view, int position) throws IOException {
//
//
//                                        if (Objects.equals(sort, "favorite")) {
//                                            Movie movie2 = db.getMovie(Integer.parseInt(movieArrayList.get(position).getMovieID()));
//                                            mListener.setSelectedMovie(movie2);
//                                        } else {
//                                            final Movie movie = movieArrayList.get(position);
//                                            mListener.setSelectedMovie(movie);
//                                        }
//                                    }
//                                    @Override
//                                    public void onLongClick(View view, int position) {
//                                    }
//                                }));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();

                        }
                    });
            MySingleton.getInstance(getContext()).addToRequestQueue(jsObjRequest);

        } else {
            Toast.makeText(getActivity(), "Error , Check your internet Connection ", Toast.LENGTH_SHORT).show();

        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
