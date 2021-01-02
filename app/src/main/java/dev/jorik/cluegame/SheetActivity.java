package dev.jorik.cluegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SheetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);
    }

    public void onCellClick(View view){
        ImageView cell = ((ImageView) view);
        new SelectIconDialog(SheetActivity.this, value -> {
            int iconId = 0;
            switch (value){
                case EMPTY: iconId = R.drawable.ic_empty; break;
                case CHECK: iconId = R.drawable.ic_check; break;
                case CROSS: iconId = R.drawable.ic_cross; break;
                case EXCLAMATION: iconId = R.drawable.ic_exclamation; break;
                case QUESTION: iconId = R.drawable.ic_question; break;
            }
            cell.setImageResource(iconId);
        }).show();
    }
}