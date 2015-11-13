package com.example.twit_graph;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class graph extends TabActivity {

	 ArrayList x=new ArrayList();
    ArrayList y=new ArrayList();

    TabHost tabHost;
    TabSpec tab1,tab2,tab3;
 
    String company1;
    String parameters1;
    String ps;
    String []a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);         
     Bundle i=getIntent().getExtras();
       //company1= i.getString("com");
 ps=i.getString("com");
  a=ps.split(":");
  company1=a[0];
   parameters1=a[1];
      // company1="yahoo";
    // parameters1="Mentions over Time";
       //Intent i=getIntent();
      // company=i.getExtras("company");
       //parameters=i.getExtras("parameters");
       
      // Pass ps=new Pass();
   // company=ps.getCompany();
   // parameters=ps.getParameters();
    
        Toast.makeText(getBaseContext(),a[1],Toast.LENGTH_LONG).show();
      //Toast.makeText(getBaseContext(),parameters,Toast.LENGTH_LONG).show();
    
        tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tab1 = tabHost.newTabSpec("Line Graph");
        tab2 = tabHost.newTabSpec("Bar Graph");
        tab3 = tabHost.newTabSpec("Pie Graph");
     
        tab1.setIndicator("Line Graph");
        tab2.setIndicator("Bar Graph");
        tab3.setIndicator("Pie Graph");
        
        if(parameters1.equals("Mentions over Time"))
        {
      	try {
				mention();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			}
        }
        else if(parameters1.equals("Top Users by no. of Followers"))
        {
        	try {
				topUsers();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else if(parameters1.equals("Response Frequency by Follower Count"))
        {
        	try {
				followers();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else if(parameters1.equals("Tweet Response Time Distribution"))
        {
        	try {
				responseTime();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
       // Set the Tab name and Activity
       // that will be opened when particular Tab will be selected     
     
        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
    }

    public void mention () throws IOException
	{
		x.clear();
		y.clear();
		String[] key=new String[1000];
		int[] value=new int[1000];
		String date = null;
		String ndate= null;
		
		int count=0;
		
		File file=new File(Environment.getExternalStorageDirectory()+"/MyFiles/",company1+".xls");
		FileInputStream myInput = new FileInputStream(file); 
        POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);		

        HSSFWorkbook wb=new HSSFWorkbook(myFileSystem);
        HSSFSheet sheet=wb.getSheetAt(1);
		
        int k=0,v=0;
        
        for (int j=1; j< sheet.getLastRowNum() + 1; j++) {
            Row row = sheet.getRow(j);
            
            count++;
            
            Cell cell = row.getCell(2); //get first cell
            ndate=cell.getDateCellValue().toString();
            ndate=ndate.substring(0,10);
            if(date==null) {
            key[k]=ndate;
            date=key[k]; k++;
            }
            else if(!date.equals(ndate))
            {
            	value[v]=count;count=1;v++;
            	key[k]=ndate;
                date=key[k];k++;
            }
            else { 
            	 }
            }
                
        value[v]=count;count=1;v++;
        int i=0;
        while(key[i]!=null)
        {
        	x.add(key[i]);
        	y.add(value[i]);
        	i++;
        }
       
        MBar bar=new MBar(x,y);
        Intent barIntent=bar.getIntent(this);
        tab2.setContent(barIntent);
        
        MLine line=new MLine(x,y);
        Intent lineIntent=line.getIntent(this);
        tab1.setContent(lineIntent);
        
        MPie pie=new MPie(x,y);
        Intent pieIntent=pie.getIntent(this);
        tab3.setContent(pieIntent);
      

	}
	
	public void responseTime () throws IOException
	{
		x.clear();
		y.clear();
		
		x.add("<30 Mins");
		x.add("30-60 Mins");
		x.add("1hr-6hrs");
		x.add("6hrs-24hrs");
		x.add(">24hrs");
		
		
		float x1=0,x2=0,x3=0,x4=0,x5=0;
		double value;
		float count = 0;
		
		File file=new File(Environment.getExternalStorageDirectory()+"/MyFiles/",company1+".xls");
		FileInputStream myInput = new FileInputStream(file); 
        POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);		

        HSSFWorkbook wb=new HSSFWorkbook(myFileSystem);
        HSSFSheet sheet=wb.getSheetAt(0);
		
        for (int j=1; j< sheet.getLastRowNum() + 1; j++) {
            Row row = sheet.getRow(j);
                        
            Cell cell=row.getCell(2);
            value= cell.getNumericCellValue();
            count++;
            		if(value<0.3) { 
            			x1++;
            		}
            		else if(value<=0.6) {
            			x2++;
            		}
            		else if(value<=6) {
            			x3++;
            		}
            		else if(value<=24) {
            			x4++;
            		}
            		else {
            			x5++;
            		}
        }	    
        
        //int i=0;
        
        	y.add((x1/count)*100);
        	y.add((x2/count)*100);
        	y.add((x3/count)*100);
        	y.add((x4/count)*100);
        	y.add((x5/count)*100);
                
        RTBar bar=new RTBar(x,y);
        Intent barIntent=bar.getIntent(this);
        tab2.setContent(barIntent);
        
        RTLine line=new RTLine(x,y);
        Intent lineIntent=line.getIntent(this);
        tab1.setContent(lineIntent);
        
        RTPie pie=new RTPie(x,y);
        Intent pieIntent=pie.getIntent(this);
        tab3.setContent(pieIntent);      
	}
	
	public void followers () throws IOException
	{
		x.clear();
		y.clear();
		
		x.add("0 to 100");
		x.add("100 to 500");
		x.add("500 t0 1,000");
		x.add("1,000 to 2,500");
		x.add(">2,500");
				
		float x1=0,x2=0,x3=0,x4=0,x5=0;
		int value;
		float count = 0;
		
		File file=new File(Environment.getExternalStorageDirectory()+"/MyFiles/",company1+".xls");
		FileInputStream myInput = new FileInputStream(file); 
        POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);		

        HSSFWorkbook wb=new HSSFWorkbook(myFileSystem);
        HSSFSheet sheet=wb.getSheetAt(1);
		
        for (int j=1; j< sheet.getLastRowNum() + 1; j++) {
            Row row = sheet.getRow(j);
            
            Cell cell=row.getCell(1);
            value=(int) cell.getNumericCellValue();
            count++;
            		if(value<=100) { 
            			x1++;
            		}
            		else if(value<=500) {
            			x2++;
            		}
            		else if(value<=1000) {
            			x3++;
            		}
            		else if(value<=2500) {
            			x4++;
            		}
            		else {
            			x5++;
            		}
        }	    
        
       // int i=0;
                
        	y.add((x1/count)*100);
        	y.add((x2/count)*100);
        	y.add((x3/count)*100);
        	y.add((x4/count)*100);
        	y.add((x5/count)*100);
        
        
        FBar bar=new FBar(x,y);
        Intent barIntent=bar.getIntent(this);
        tab2.setContent(barIntent);
        
        FLine line=new FLine(x,y);
        Intent lineIntent=line.getIntent(this);
        tab1.setContent(lineIntent);
        
        FPie pie=new FPie(x,y);
        Intent pieIntent=pie.getIntent(this);
        tab3.setContent(pieIntent);
       
	}
	
	public void topUsers () throws IOException
	{
		x.clear();
		y.clear();
		String[] key=new String[300];
		int[] value=new int[300];
		
		File file=new File(Environment.getExternalStorageDirectory()+"/MyFiles/",company1+".xls");
		FileInputStream myInput = new FileInputStream(file); 
        POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);		

        HSSFWorkbook wb=new HSSFWorkbook(myFileSystem);
        HSSFSheet sheet=wb.getSheetAt(2);
		
        for (int j=1; j< sheet.getLastRowNum() + 1; j++) {
            Row row = sheet.getRow(j);
            
            Cell cell = row.getCell(0); //get first cell
            key[j-1]=cell.getStringCellValue();
            
            cell=row.getCell(1);
            value[j-1]=(int) cell.getNumericCellValue();
        }
        
        int swap=0;
        String swp;
        for (int c = 0; c < ( value.length - 1 ); c++) {
            for (int d = 0; d < value.length - c - 1; d++) {
              if ((value[d] < value[d+1])) /* For descending order use < */
              {
                swap       = value[d];
                value[d]   = value[d+1];
                value[d+1] = swap;
                swp=key[d];
                key[d]=key[d+1];
                key[d+1]=swp;
              }
            }
          }
        
        int i=0;
        while(key[i]!=null)
        {
        	x.add(key[i]);
        	y.add(value[i]);
        	i++;
        }
        
        TUBar bar=new TUBar(x,y);
        Intent barIntent=bar.getIntent(this);
        tab2.setContent(barIntent);
        
        
        TULine line=new TULine(x,y);
        Intent lineIntent=line.getIntent(this);
        tab1.setContent(lineIntent);
        
        TUPie pie=new TUPie(x,y);
        Intent pieIntent=pie.getIntent(this);
        tab3.setContent(pieIntent);
	}    
}
