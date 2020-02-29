package com.example.testapplication.model;

import com.google.gson.annotations.SerializedName;

public class Type{

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"Type{" + 
			"id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}