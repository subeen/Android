package com.example.twit_graph;

public class Pass {

	public String company;
	public String parameters;

 public Pass()
 {}
	public Pass(String cmp,String parm)
	{
		this.company=cmp;
		this.parameters=parm;
	}

	public void setCompany(String cmp)
	{
		this.company=cmp;
	}

	public void setParameters(String parm)
	{
		this.parameters=parm;
	}

	public String getCompany()
	{
		return this.company;	
	}

	public String getParameters()
	{
		return this.parameters;	
	}	
}