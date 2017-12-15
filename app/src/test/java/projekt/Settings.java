package projekt;




import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;

import cz.vlk.hra.R;


public class Settings extends Activity {

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    Boolean zvuk;
    int obtiznost, barva1, barva2;
    Spinner sp_obtiznost, sp_barva1, sp_barva2;
    CheckBox cb_zvuk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // načte nastavení
        sp = getSharedPreferences("nastaveni", 0);
        obtiznost = sp.getInt("obtiznost", 1);
        barva1 = sp.getInt("barva1", 1);
        barva2 = sp.getInt("barva2", 1);
        zvuk = sp.getBoolean("mute", true);


        sp_obtiznost = (Spinner) findViewById(R.id.Obtiznost);
        sp_barva1 = (Spinner) findViewById(R.id.Barva_hada);
        sp_barva2 = (Spinner) findViewById(R.id.Barva_hada_ocas);
        cb_zvuk = (CheckBox) findViewById(R.id.cb_zvuk);


        // nastaví počateční stav prvku podle nastavení
        sp_obtiznost.setSelection(obtiznost);
        sp_barva1.setSelection(barva1);
        sp_barva2.setSelection(barva2);
        cb_zvuk.setChecked(zvuk);
    }


    // Metoda je volána po stisku tlačítka Smazat highscore
    public void smazat(View view){
        spEditor = sp.edit();
        spEditor.putInt("nejscore", 0).apply();
    }


    // Uloží nastavení
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        spEditor = sp.edit();
        spEditor.putInt("obtiznost", sp_obtiznost.getSelectedItemPosition()).apply();
        spEditor.putInt("barva1", sp_barva1.getSelectedItemPosition()).apply();
        spEditor.putInt("barva2", sp_barva2.getSelectedItemPosition()).apply();
        spEditor.putBoolean("mute", cb_zvuk.isChecked()).apply();
        finish();
    }

    // Metoda je volána po stisku tlačítka ulozit
    public void ulozit(View view){
        onBackPressed();
    }
}
