package com.example.testapplication.productAnswerModel;

import com.google.gson.annotations.SerializedName;

public class Brand{

	@SerializedName("image")
	private String image;

	@SerializedName("name")
	private String name;

	@SerializedName("altName")
	private String altName;

	@SerializedName("id")
	private int id;

	@SerializedName("url")
	private String url;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setAltName(String altName){
		this.altName = altName;
	}

	public String getAltName(){
		return altName;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"Brand{" + 
			"image = '" + image + '\'' + 
			",name = '" + name + '\'' + 
			",altName = '" + altName + '\'' + 
			",id = '" + id + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}