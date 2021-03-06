package com.example.twit_graph;

import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class FBar {

	ArrayList x=new ArrayList();
    ArrayList y=new ArrayList();

	    public FBar(ArrayList ox,ArrayList oy)
	    {
	    	x=ox;
	    	y=oy;
	    }
		
	    public Intent getIntent(Context context)
	    {
	    	TimeSeries series=new TimeSeries("Reponse Frequency");
	    	for(int i=0;i<y.size();i++)
	    	{
	    		series.add(i+1, Float.parseFloat(y.get(i).toString()));
	    	}
	    	
	    	XYMultipleSeriesDataset dataset=new XYMultipleSeriesDataset();
			dataset.addSeries(series);
			
			XYSeriesRenderer renderer=new XYSeriesRenderer();
			renderer.setColor(Color.CYAN);
			
			XYMultipleSeriesRenderer mRenderer=new XYMultipleSeriesRenderer();
			mRenderer.addSeriesRenderer(renderer);
			mRenderer.setChartTitle("Reponse Frequency");
			mRenderer.setXTitle("Category");
			mRenderer.setYTitle("Percentage");
			mRenderer.setBarSpacing(2.0);
			mRenderer.setBackgroundColor(Color.BLACK);
			mRenderer.setApplyBackgroundColor(true);
			
			mRenderer.setXLabelsAngle(-45);
			mRenderer.setXAxisMin(0);
			mRenderer.setYAxisMin(0);
//			mRenderer.setZoomButtonsVisible(true);
			
			for(int i=0;i<x.size();i++)
	    	{
	    		mRenderer.addXTextLabel(i+1,x.get(i).toString());
	    	}
			
			
			Intent intent=ChartFactory.getBarChartIntent(context, dataset, mRenderer, Type.DEFAULT);
			return intent;
	    }
	    
	}
