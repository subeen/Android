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
public class parameters extends Activity {
	private String array_spinner[];
	String company;
	Spinner s;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parameters);
        Bundle i=getIntent().getExtras();
        company = i.getString("company");
       // Toast.makeText(getBaseContext(),company,Toast.LENGTH_LONG).show();
        s= (Spinner) findViewById(R.id.spinner1);
        array_spinner = new String[5];
        array_spinner[0]= "Select Parameters";
        array_spinner[1]= "Tweet Response Time Distribution";
        array_spinner[2]= "Response Frequency by Follower Count";
        array_spinner[3]= "Top Users by no. of Followers";
      array_spinner[4]= "Mentions over Time";
        
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);
        Button graph = (Button)findViewById(R.id.button1);       
        graph.setOnClickListener(show_graph);
    }
	 private OnClickListener show_graph = new OnClickListener() {
	        @Override
			public void onClick(View v) {
			
			//Pass ps=new Pass();
   // ps.setParameters(array_spinner[s.getSelectedItemPosition()]);
     if(s.getSelectedItemPosition()==0)
    {
    Toast.makeText(getBaseContext(),"select parameters",Toast.LENGTH_LONG).show();
    }
    else
    {
	        	Intent intent = new Intent(parameters.this, graph.class);
	       	intent.putExtra("com",company+":"+ array_spinner[s.getSelectedItemPosition()]);
	           
	          
	            startActivity(intent); 
	            
	        }
	        }
	    };
}
