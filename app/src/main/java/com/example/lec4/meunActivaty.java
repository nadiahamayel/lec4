package com.example.lec4;

import androidx.appcompat.app.AppCompatActivity;
import model.IItemsData;
import model.FoodItem;
import model.ItemsData;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.List;
import android.os.Bundle;

public class meunActivaty extends AppCompatActivity {
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


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = spn.getSelectedItem().toString();
                List<FoodItem> result = data.getItemsByCat(str);
                ArrayAdapter<FoodItem> adapterItems = new ArrayAdapter<FoodItem>(meunActivaty.this,
                        android.R.layout.simple_list_item_1, result);
                lst.setAdapter(adapterItems);
            }
        });
    }
}