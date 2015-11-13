package com.example.twit_graph;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
public class company extends Activity {
	String array_spinner[];
	Spinner s;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_analysis);
        s= (Spinner) findViewById(R.id.spinner1);
        array_spinner = new String[5];
        array_spinner[0]= "Select Company";
        array_spinner[1]= "microsoft";
        array_spinner[2]= "google";
        array_spinner[3]= "adobe";
      array_spinner[4]= "yahoo";
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);
        Button company = (Button)findViewById(R.id.button1);       
        company.setOnClickListener(company1);
             
    }
    private OnClickListener company1 = new OnClickListener() {
        @Override
		public void onClick(View v) {
		
		/*Pass ps=new Pass();
    ps.setCompany(array_spinner[s.getSelectedItemPosition()]);*/
    if(s.getSelectedItemPosition()==0)
    {
    Toast.makeText(getBaseContext(),"select a company",Toast.LENGTH_LONG).show();
    }
    else
    {
        	Intent intent = new Intent(company.this, parameters.class);
      	intent.putExtra("company", array_spinner[s.getSelectedItemPosition()]);  
            startActivity(intent); 
            }
        }
    };
}
