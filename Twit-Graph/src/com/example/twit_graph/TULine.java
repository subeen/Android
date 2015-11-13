package com.example.twit_graph;

import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class TULine {

	ArrayList x=new ArrayList();
    ArrayList y=new ArrayList();

    public TULine(ArrayList ox,ArrayList oy)
    {
    	x=ox;
    	y=oy;
    }
	
	public Intent getIntent(Context context) 
	{

		TimeSeries series= new TimeSeries("No. of Followers");
		for(int i=0;i<20;i++)
    	{
    		series.add(i+1, Integer.parseInt(y.get(i).toString()));
    	}

		XYMultipleSeriesDataset dataset=new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		
		XYSeriesRenderer renderer=new XYSeriesRenderer();
		renderer.setPointStyle(PointStyle.SQUARE);
		renderer.setFillPoints(true);
		renderer.setColor(Color.MAGENTA);

		XYMultipleSeriesRenderer mRenderer=new XYMultipleSeriesRenderer();
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.setChartTitle("Top Users");
		mRenderer.setXTitle("UserName");
		mRenderer.setYTitle("Followers");
		mRenderer.setBackgroundColor(Color.BLACK);
		mRenderer.setApplyBackgroundColor(true);
		
		mRenderer.setXLabelsAngle(-45);
		mRenderer.setXAxisMin(0);
		mRenderer.setYAxisMin(0);
//		mRenderer.setZoomButtonsVisible(true);
		
		for(int i=0;i<20;i++)
    	{
    		mRenderer.addXTextLabel(i+1,x.get(i).toString());
    	}
		
		Intent intent=ChartFactory.getLineChartIntent(context, dataset, mRenderer);
		return intent;
	}
}
