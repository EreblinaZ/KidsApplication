
package com.projekti.kidsapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AboutUsActivity extends AppCompatActivity {

    ConstraintLayout aboutus;
    Button btnCall;
    Button btnMail;
    Button btnpopUp;
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        btnMail=findViewById(R.id.btnMail);
        btnCall=findViewById(R.id.btnCall);
        aboutus=findViewById(R.id.aboutus);
        txt = findViewById(R.id.txt);
        btnpopUp=findViewById(R.id.btnpopUp);
        parseXML();


        btnMail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final Snackbar snackbar=Snackbar.make(aboutus, "Send mail to us", Snackbar.LENGTH_INDEFINITE);

                snackbar.setAction("Dismiss", new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        snackbar.dismiss();
                    }
                });
                snackbar.setDuration(7000);
                snackbar.show();
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Snackbar snackbar=Snackbar.make(aboutus, "See our phone numbers in the icon", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.setDuration(7000);
                snackbar.show();
            }
        });
    }
    public void popUpMenu(View v){
        PopupMenu p=new PopupMenu(AboutUsActivity.this, btnpopUp);
        p.getMenuInflater().inflate(R.menu.popup_menu,p.getMenu());
        p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(AboutUsActivity.this,menuItem.getTitle(), Toast.LENGTH_LONG).show();;
                return true;
            }
        });
        p.show();
    }





    private void parseXML() {
        XmlPullParserFactory parserFactory;
        try {
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            InputStream is = getAssets().open("data.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);

            processParsing(parser);

        } catch (XmlPullParserException | IOException e) {

        }
    }

    private void processParsing(XmlPullParser parser) throws IOException, XmlPullParserException{
        ArrayList<Data> datas = new ArrayList<>();
        int eventType = parser.getEventType();
        Data currentData= null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String eltName;

            switch (eventType) {
                case XmlPullParser.START_TAG:
                    eltName = parser.getName();

                    if ("data".equals(eltName)) {
                        currentData = new Data();
                        datas.add(currentData);
                    } else if (currentData != null) {
                        if ("seller".equals(eltName)) {
                            currentData.seller= parser.nextText();
                        } else if ("category".equals(eltName)) {
                            currentData.category = parser.nextText();
                        } else if ("language".equals(eltName)) {
                            currentData.language = parser.nextText();
                        } else if ("age".equals(eltName)){
                            currentData.age=parser.nextText();
                        } else if("price".equals(eltName)){
                            currentData.price=parser.nextText();
                        }else if("copyright".equals(eltName)){
                            currentData.copyright=parser.nextText();
                        }

                    }
                    break;

            }

            eventType = parser.next();
        }

        printDatas(datas);
    }

    private void printDatas(ArrayList<Data> datas) {
        StringBuilder builder=new StringBuilder();



        for (Data data : datas) {
            builder.append(data.seller).append("\n").
                    append(data.category).append("\n").
                    append(data.language).append("\n").
                    append(data.age).append("\n").
                    append(data.price).append("\n").
                    append(data.copyright).append("\n\n");
        }

        txt.setText(builder.toString());
    }


}