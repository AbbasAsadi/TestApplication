package com.example.testapplication.productAnswerModel;

import com.google.gson.annotations.SerializedName;

public class Unit{

	@SerializedName("decimalPlaces")
	private int decimalPlaces;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public void setDecimalPlaces(int decimalPlaces){
		this.decimalPlaces = decimalPlaces;
	}

	public int getDecimalPlaces(){
		return decimalPlaces;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Unit{" + 
			"decimalPlaces = '" + decimalPlaces + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}