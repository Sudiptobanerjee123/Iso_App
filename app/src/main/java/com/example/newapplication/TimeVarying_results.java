package com.example.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TimeVarying_results extends AppCompatActivity {

//    private static final String FILENAME = "MyData.txt";




    TextView q1,q2,q3,q4,q5,R1,R2,R3,R4,R5,viewq1,viewq2,viewq3,viewq4,viewq5,viewR1,viewR2,viewR3,viewR4,viewR5;
    Button btn_show_graph, btn_back_time_varying, btn_save;

    String q_1,q_2, q_3, q_4, q_5, R_1, R_2, R_3, R_4, R_5;
    Member member;

    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_varying_results);


        q1 = (TextView)findViewById(R.id.q1_text);
        q2 = (TextView)findViewById(R.id.q2_text);
        q3 = (TextView)findViewById(R.id.q3_text);
        q4 = (TextView)findViewById(R.id.q4_text);
        q5 = (TextView)findViewById(R.id.q5_text);
        R1 = (TextView)findViewById(R.id.R1_text);
        R2 = (TextView)findViewById(R.id.R2_text);
        R3 = (TextView)findViewById(R.id.R3_text);
        R4 = (TextView)findViewById(R.id.R4_text);
        R5 = (TextView)findViewById(R.id.R5_text);


       viewq1 = (TextView)findViewById(R.id.text_q1);
       viewq2 = (TextView)findViewById(R.id.text_q2);
       viewq3 = (TextView)findViewById(R.id.text_q3);
       viewq4 = (TextView)findViewById(R.id.text_q4);
       viewq5 = (TextView)findViewById(R.id.text_q5);
       viewR1 = (TextView)findViewById(R.id.text_R1);
       viewR2 = (TextView)findViewById(R.id.text_R2);
       viewR3 = (TextView)findViewById(R.id.text_R3);
       viewR4 = (TextView)findViewById(R.id.text_R4);
       viewR5 = (TextView)findViewById(R.id.text_R5);

       btn_show_graph = (Button)findViewById(R.id.Next_Graph);

        btn_back_time_varying = (Button)findViewById(R.id.back_results);
        btn_save = (Button)findViewById(R.id.save_time_results);

        member = new Member();
        reff = FirebaseDatabase.getInstance().getReference().child("Member");


       q_1 = getIntent().getExtras().getString("q1");
       q_2 = getIntent().getExtras().getString("q2");
       q_3 = getIntent().getExtras().getString("q3");
       q_4 = getIntent().getExtras().getString("q4");
       q_5 = getIntent().getExtras().getString("q5");

       R_1 = getIntent().getExtras().getString("R1");
       R_2 = getIntent().getExtras().getString("R2");
       R_3 = getIntent().getExtras().getString("R3");
       R_4 = getIntent().getExtras().getString("R4");
       R_5 = getIntent().getExtras().getString("R5");



       viewq1.setText(q_1);
       viewq2.setText(q_2);
       viewq3.setText(q_3);
       viewq4.setText(q_4);
       viewq5.setText(q_5);

       viewR1.setText(R_1);
       viewR2.setText(R_2);
       viewR3.setText(R_3);
       viewR4.setText(R_4);
       viewR5.setText(R_5);




        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String save_time = btn_save.getText().toString().trim();
                reff.child("Langmuir").setValue(member);
                Toast.makeText(TimeVarying_results.this, "data inserted successfully", Toast.LENGTH_LONG).show();

                if(btn_save.getText().equals(save_time))
                {
                    String newq1 = viewq1.getText().toString();
                    String newq2 = viewq2.getText().toString();
                    String newq3 = viewq3.getText().toString();
                    String newq4 = viewq4.getText().toString();
                    String newq5 = viewq5.getText().toString();

                    String newR1 = viewR1.getText().toString();
                    String newR2 = viewR2.getText().toString();
                    String newR3 = viewR3.getText().toString();
                    String newR4 = viewR4.getText().toString();
                    String newR5 = viewR5.getText().toString();

                    member.setQe1(newq1);
                    member.setQe2(newq2);
                    member.setQe3(newq3);
                    member.setQe4(newq4);
                    member.setQe5(newq5);

                    member.setRe1(newR1);
                    member.setRe2(newR2);
                    member.setRe3(newR3);
                    member.setRe4(newR4);
                    member.setRe5(newR5);

                }

            }
        });


       btn_show_graph.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               String graph= btn_show_graph.getText().toString().trim();
               if(btn_show_graph.getText().equals(graph))
               {


                   startActivity(new Intent(getApplicationContext(),Graph_page.class));



               }
           }
       });

       btn_back_time_varying.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String back = btn_back_time_varying.getText().toString().trim();

               if(btn_back_time_varying.getText().equals(back))
               {
                   startActivity(new Intent(getApplicationContext(),Langmuir.class));

               }

           }
       });

    }
//    public void save(View view)
//    {
//        String newq1 = viewq1.getText().toString();
//        String newq2 = viewq2.getText().toString();
//        String newq3 = viewq3.getText().toString();
//        String newq4 = viewq4.getText().toString();
//        String newq5 = viewq5.getText().toString();
//        FileOutputStream fos =null;
//
//        try {
//            fos = openFileOutput(FILENAME, MODE_PRIVATE);
//
//            fos.write(newq1.getBytes());
//            fos.write(newq2.getBytes());
//            fos.write(newq3.getBytes());
//            fos.write(newq4.getBytes());
//            fos.write(newq5.getBytes());
//
//            Toast.makeText(this, "Saved to" + getFilesDir() + "/" + FILENAME, Toast.LENGTH_LONG).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if(fos != null)
//            {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }


}
