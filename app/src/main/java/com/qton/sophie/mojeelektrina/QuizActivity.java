package com.qton.sophie.mojeelektrina;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    TextView question;
    RadioButton button1;
    RadioButton button2;
    RadioButton button3;
    RadioButton button4;
    FloatingActionButton submit;
    int score = 0;
    int questionNumber = 2;
    String good = "Správně";
    String bad = "Špatně";
    String result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        findViews();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getQuestion();
            }
        });

    }

    void getQuestion() {
        switch (questionNumber++) {
            case 2:
                //update score
                result = bad;
                if (button1.isChecked()) {
                    score++;
                    result = good;
                }
                findViews();
                //clear checked buttons
                clearChoices();
                //fill a new view
                question.setText("Když budu měsíc hodinu denně vyrábět energii jízdou na rotopedu, vyrobím energii na provoz pouliční lampy na:");
                button1.setText("10");
                button2.setText("15");
                button3.setText("20");
                button4.setText("25");
                //Toast message
                toast("3000 Kč", result);
                break;
            case 3:
                //update score
                result = bad;
                if (button4.isChecked()) {
                    result = good;
                    score++;
                }
                findViews();
                //clear checked buttons
                clearChoices();
                //fill a new view
                question.setText("Při efektivním využití myčky a pračky v období s nízkým tarifem mohu ušetřit dost energie na:");
                button1.setText("Měsíc provozu pouliční lampy");
                button2.setText("Čtvrt roku provozu pouliční lampy");
                button3.setText("Půl roku provozu pouliční lampy");
                button4.setText("Tři čtvrtě roku provozu pouliční lampy");
                //Toast message
                toast("25 hodin", result);
                break;
            case 4:
                //update score
                result = bad;
                if (button3.isChecked()) {
                    result = good;
                    score++;
                }
                findViews();
                //clear checked buttons
                clearChoices();
                //fill a new view
                question.setText("Energie potřebná pro rok ohřevu 100-litrového bojleru dokáže po celou dobu adventu osvětlit vánoční stromky v kolika městech?");
                button1.setText("4");
                button2.setText("5");
                button3.setText("6");
                button4.setText("7");
                //Toast message
                toast("Na půl roku", result);
                break;
            case 5:
                //update score
                result = bad;
                if (button3.isChecked()) {
                    score++;
                    result = good;
                }
                findViews();
                //clear checked buttons
                clearChoices();
                //fill a new view
                question.setText("Když budu měsíc půl hodinu denně vyrábět energii jízdou na rotopedu, vyrobím energii, která pokryje:");
                button1.setText("0,015% měsíční spotřeby průměrné domácnosti");
                button2.setText("0,15% měsíční spotřeby průměrné domácnosti");
                button3.setText("1,5% měsíční spotřeby průměrné domácnosti");
                button4.setText("15% měsíční spotřeby průměrné domácnosti");
                //Toast message
                toast("V 6 městech", result);
                break;
            case 6:
                //update score
                result = bad;
                if (button1.isChecked()) {
                    score++;
                    result = good;
                }
                findViews();
                //clear checked buttons
                clearChoices();
                //fill a new view
                question.setText("Kolik stojí energie, kterou spotřebuje domácí pekárna na upečení jednoho chleba?");
                button1.setText("2 Kč");
                button2.setText("5 Kč");
                button3.setText("7 Kč");
                button4.setText("11 Kč");
                //Toast message
                toast("0,015% měsíční spotřeby", result);
                break;
            case 7:
                result = bad;
                //update score
                if (button4.isChecked()) {
                    score++;
                    result = good;
                }
                findViews();
                //clear checked buttons
                clearChoices();
                //fill a new view
                question.setText("Roční provoz hokejové haly v Mnichově potřebuje energii, kterou spotřebují:");
                button1.setText("dvě pouliční lampy za půl roku");
                button2.setText("dvě pouliční lampy za jeden rok");
                button3.setText("dvě pouliční lampy během dvou let");
                button4.setText("dvě pouliční lampy během deseti let");
                //Toast message
                toast("11 Kč", result);
                break;
            case 8:
                //update score
                result = bad;
                if (button2.isChecked()) {
                    score++;
                    result = good;
                }
                findViews();
                //clear checked buttons
                clearChoices();
                //fill a new view
                question.setText("Kolik stojí energie, kterou spotřebuje průměrné akvárium za jeden rok?");
                button1.setText("800 Kč");
                button2.setText("1200 Kč");
                button3.setText("1600 Kč");
                button4.setText("2000 Kč");
                //Toast message
                toast("Za jeden rok", result);
                break;
            case 9:
                //update score
                result = bad;
                if (button3.isChecked()) {
                    score++;
                    result = good;
                }
                findViews();
                //clear checked buttons
                clearChoices();
                //fill a new view
                question.setText("K internetu je připojeno 14 miliard zařízení jejichž spotřeba se rovná spotřebě:");
                button1.setText("celé Kanady");
                button2.setText("celého Slovenska");
                button3.setText("celého Německa");
                button4.setText("celé Velké Británie");
                //Toast message
                toast("1600 Kč", result);
                break;
            case 10:
                result = bad;
                //update score
                if (button1.isChecked()) {
                    score++;
                    result = good;
                }
                findViews();
                //clear checked buttons
                clearChoices();
                //fill a new view
                question.setText("Těžba bitcoinů za rok spotřebuje celosvětově tolik energie jako");
                button1.setText("55 000 pouličních lamp");
                button2.setText("550 000 pouličních lamp");
                button3.setText("5,5 milionu pouličních lamp");
                button4.setText("55 milionů pouličních lamp");
                //Toast message
                toast("celé Kanady", result);
                break;
            default:
                result = bad;
                if (button3.isChecked()) {
                    score++;
                    result = good;
                }

                lastDialog("5,5 milionu pouličních lamp. \nVaše skóre je " + score, result);

        }
    }

    void toast(String text, String title) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(text);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    void lastDialog(String text, String title) {
        final Intent myIntent = new Intent(this, MainActivity.class);
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(text);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(myIntent);
                    }
                });
        alertDialog.show();
    }

    public void clearChoices() {
        button1.setChecked(false);
        button2.setChecked(false);
        button3.setChecked(false);
        button4.setChecked(false);

    }

    public void findViews() {
        question = findViewById(R.id.RBquestion);
        button1 = findViewById(R.id.RBanswer1);
        button2 = findViewById(R.id.RBanswer2);
        button3 = findViewById(R.id.RBanswer3);
        button4 = findViewById(R.id.RBanswer4);
        submit = findViewById(R.id.submit_btn);
    }


}
