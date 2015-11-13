package com.example.twit_graph;

import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class FPie {

	ArrayList x=new ArrayList();
    ArrayList y=new ArrayList();

    public FPie(ArrayList ox,ArrayList oy)
    {
    	x=ox;
    	y=oy;
    }
	
	public Intent getIntent(Context context)
	{
		CategorySeries series=new CategorySeries("Pie Graph");
		int k=0;
		for (;k<y.size();k++)
		{
			series.add(x.get(k).toString(),Float.parseFloat(y.get(k).toString()));
		}
		
		int[] colors=new int[] {Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN };
		
		DefaultRenderer renderer=new DefaultRenderer();
		renderer.setBackgroundColor(Color.BLACK);
		renderer.setApplyBackgroundColor(true);
		renderer.setChartTitle("Reponse Frequency");
		for(int color:colors)
		{
			SimpleSeriesRenderer r=new SimpleSeriesRenderer();
			r.setColor(color);
			renderer.addSeriesRenderer(r);
		}
		
		Intent intent=ChartFactory.getPieChartIntent(context, series, renderer, "TwitGraph");
		return intent;
	}
}
