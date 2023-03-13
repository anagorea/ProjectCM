package com.example.ecommerce3;

import android.hardware.lights.LightState;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.ecommerce3.adapters.HomeAdapter;
import com.example.ecommerce3.adapters.PopularAdapter;
import com.example.ecommerce3.adapters.PopularAdapter;
import com.example.ecommerce3.adapters.RecommendedAdapter;
import com.example.ecommerce3.models.HomeCategory;
import com.example.ecommerce3.models.PopularModel;
import com.example.ecommerce3.models.RecommendedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    ScrollView scrollView;
    ProgressBar progressBar;

    RecyclerView popularRec,homeCatRec,recommendedRec;
    FirebaseFirestore db;

    //popular items
    List<PopularModel> popularModelList;
    PopularAdapter popularAdapters;

    //home Category
    List<HomeCategory> categoryList;
    HomeAdapter homeAdapter;

    //Recommended
    List<RecommendedModel> recommendedModelList;
    RecommendedAdapter recommendedAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        db = FirebaseFirestore.getInstance();

        popularRec = root.findViewById(R.id.pop_rec);
        homeCatRec = root.findViewById(R.id.explore_rec);
        recommendedRec = root.findViewById(R.id.recommended_rec);
        scrollView = root.findViewById(R.id.scroll_view);
        progressBar = root.findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);


        //Popular items
        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModelList = new ArrayList<>();
        popularAdapters = new PopularAdapter(getActivity(),popularModelList);
        popularRec.setAdapter(popularAdapters);

        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapters.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);


                            }
                        } else {

                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        //Home Category
        homeCatRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryList = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(),categoryList);
        homeCatRec.setAdapter(homeAdapter);

        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                HomeCategory homeCategory = document.toObject(HomeCategory.class);
                                categoryList.add(homeCategory);
                                homeAdapter.notifyDataSetChanged();

                            }
                        } else {

                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        //Home Category
        recommendedRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recommendedModelList = new ArrayList<>();
        recommendedAdapter = new RecommendedAdapter(getActivity(),recommendedModelList);
        recommendedRec.setAdapter(recommendedAdapter);

        db.collection("Recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                RecommendedModel recommendedModel = document.toObject(RecommendedModel.class);
                                recommendedModelList.add(recommendedModel);
                                recommendedAdapter.notifyDataSetChanged();

                            }
                        } else {

                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });




        return root;
    }
}