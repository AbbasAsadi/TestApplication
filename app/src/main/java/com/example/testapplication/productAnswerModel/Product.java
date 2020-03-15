package com.example.testapplication.productAnswerModel;

import com.google.gson.annotations.SerializedName;

public class Product{

	@SerializedName("image")
	private String image;

	@SerializedName("unit")
	private Unit unit;

	@SerializedName("productId")
	private int productId;

	@SerializedName("alert")
	private String alert;

	@SerializedName("altTitle")
	private String altTitle;

	@SerializedName("title")
	private String title;

	@SerializedName("type")
	private Type type;

	@SerializedName("brand")
	private Brand brand;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setUnit(Unit unit){
		this.unit = unit;
	}

	public Unit getUnit(){
		return unit;
	}

	public void setProductId(int productId){
		this.productId = productId;
	}

	public int getProductId(){
		return productId;
	}

	public void setAlert(String alert){
		this.alert = alert;
	}

	public String getAlert(){
		return alert;
	}

	public void setAltTitle(String altTitle){
		this.altTitle = altTitle;
	}

	public String getAltTitle(){
		return altTitle;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setType(Type type){
		this.type = type;
	}

	public Type getType(){
		return type;
	}

	public void setBrand(Brand brand){
		this.brand = brand;
	}

	public Brand getBrand(){
		return brand;
	}

	@Override
 	public String toString(){
		return 
			"Product{" + 
			"image = '" + image + '\'' + 
			",unit = '" + unit + '\'' + 
			",productId = '" + productId + '\'' + 
			",alert = '" + alert + '\'' + 
			",altTitle = '" + altTitle + '\'' + 
			",title = '" + title + '\'' + 
			",type = '" + type + '\'' + 
			",brand = '" + brand + '\'' + 
			"}";
		}
}