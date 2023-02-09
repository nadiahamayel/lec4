package com.example.lec4;
//firebase
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.IItemsData;
import model.FoodItem;
import model.ItemsData;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class meunActivaty extends AppCompatActivity {
    //firebase
    private DatabaseReference mDatabase;
    private Spinner spn;
    private ListView lst;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meun_activaty);
        spn = findViewById(R.id.spnCats);
        lst = findViewById(R.id.lstItems);
        btn = findViewById(R.id.btnShow);

        IItemsData data = new ItemsData();
        List<String> Cats = data.getCategories();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Cats);
        spn.setAdapter(arrayAdapter);
        //firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = spn.getSelectedItem().toString();
                List<FoodItem> result1 = data.getItemsByCat(str);
                // Save the data to Firebase
                for (FoodItem item : result1) {
                    mDatabase.child("items").push().setValue(item);
                }

                    mDatabase.child("items").addValueEventListener(new ValueEventListener() {

                        List<FoodItem> result = new ArrayList<>();
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                                FoodItem item = itemSnapshot.getValue(FoodItem.class);
                                if (item.getCat().equals(str)) {
                                    result.add(item);
                                }
                            }
                            ArrayAdapter<FoodItem> adapterItems = new ArrayAdapter<FoodItem>(meunActivaty.this,
                                    android.R.layout.simple_list_item_1, result);
                            lst.setAdapter(adapterItems);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            });



        }
}