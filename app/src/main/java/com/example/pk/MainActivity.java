package com.example.pk;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private CombateViewModel combateViewModel;
    private Button btnReiniciarCombate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        combateViewModel = new ViewModelProvider(this).get(CombateViewModel.class);
        btnReiniciarCombate = findViewById(R.id.botonreiniciar);

        // Observamos el texto de combate para actualizar la UI
        combateViewModel.getTextoCombate().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView textoCombate = findViewById(R.id.texto);
                textoCombate.setText(s);
            }
        });

        // Configuramos el botón de reiniciar combate
        btnReiniciarCombate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combateViewModel.reiniciarCombate();
            }
        });
    }

}
