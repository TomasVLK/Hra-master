package projekt;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;




public class MainActivity extends Activity {

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    Button btn_mute;
    TextView txv_nejscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sp = getSharedPreferences("nastaveni", 0);
        btn_mute = (Button) findViewById(R.id.btn_mute);
        txv_nejscore = (TextView) findViewById(R.id.txv_nejscore);


        if (!sp.getBoolean("mute", true)) {
            btn_mute.setText("Zapnout vibrace");
        }


        if(sp.getInt("nejscore", 0) == 0) {
            txv_nejscore.setText("Zatím si nenahrál nic.");
        } else {
            txv_nejscore.setText(Integer.toString(sp.getInt("nejscore", 0)));
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        if(sp.getInt("nejscore", 0) == 0) {
            txv_nejscore.setText("Zatím si nenahrál nic.");
        } else {
            txv_nejscore.setText("Tvoje nejlepší score je " + Integer.toString(sp.getInt("nejscore", 0)));
        }
    }

   
    public void start(View view){
        Intent intent = new Intent(this, Gamelist.class);
        startActivity(intent);
    }
    public void nastaveni(View view){
        Intent intent = new Intent(this, projekt.Settings.class);
        startActivity(intent);
    }
    public void mute(View view){
        spEditor = sp.edit();
        if (sp.getBoolean("mute", true)) {
            spEditor.putBoolean("mute", false).apply();
            btn_mute.setText("Zapnout vibrace");
        } else {
            spEditor.putBoolean("mute", true).apply();
            btn_mute.setText("Vypnout vibrace");
            Vibrator v = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
            v.vibrate(50);
        }
    }
    public void ukoncit (View view){
        finish();
    }
}
