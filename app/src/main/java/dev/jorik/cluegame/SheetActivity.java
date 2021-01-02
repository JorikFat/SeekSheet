package dev.jorik.cluegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SheetActivity extends AppCompatActivity {
    private View.OnClickListener cellClickListener = v -> new SelectIconDialog(SheetActivity.this, value -> {
        switch (value){
            case EMPTY: ((ImageView)v).setImageResource(R.drawable.ic_empty); break;
            case CHECK: ((ImageView)v).setImageResource(R.drawable.ic_check); break;
            case CROSS: ((ImageView)v).setImageResource(R.drawable.ic_cross); break;
            case EXCLAMATION: ((ImageView)v).setImageResource(R.drawable.ic_exclamation); break;
            case QUESTION: ((ImageView)v).setImageResource(R.drawable.ic_question); break;
        }
    }).show();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);
        View row = findViewById(R.id.tr_sheet_personGreen);
        row.findViewById(R.id.iv_sheet_player).setOnClickListener(cellClickListener);
        row.findViewById(R.id.iv_sheet_player1).setOnClickListener(cellClickListener);
        row.findViewById(R.id.iv_sheet_player2).setOnClickListener(cellClickListener);
        row.findViewById(R.id.iv_sheet_player3).setOnClickListener(cellClickListener);
        row.findViewById(R.id.iv_sheet_player4).setOnClickListener(cellClickListener);
        row.findViewById(R.id.iv_sheet_player5).setOnClickListener(cellClickListener);
    }
}