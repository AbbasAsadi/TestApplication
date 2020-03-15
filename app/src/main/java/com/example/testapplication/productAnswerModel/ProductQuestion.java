package com.example.testapplication.productAnswerModel;

import com.google.gson.annotations.SerializedName;

public class ProductQuestion{

	@SerializedName("product")
	private Product product;

	@SerializedName("productId")
	private int productId;

	@SerializedName("created")
	private String created;

	@SerializedName("id")
	private int id;

	@SerializedName("body")
	private String body;

	@SerializedName("status")
	private String status;

	public void setProduct(Product product){
		this.product = product;
	}

	public Product getProduct(){
		return product;
	}

	public void setProductId(int productId){
		this.productId = productId;
	}

	public int getProductId(){
		return productId;
	}

	public void setCreated(String created){
		this.created = created;
	}

	public String getCreated(){
		return created;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setBody(String body){
		this.body = body;
	}

	public String getBody(){
		return body;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ProductQuestion{" + 
			"product = '" + product + '\'' + 
			",productId = '" + productId + '\'' + 
			",created = '" + created + '\'' + 
			",id = '" + id + '\'' + 
			",body = '" + body + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}