package com.example.testapplication.productAnswerModel;


import com.google.gson.annotations.SerializedName;

public class ResponseBody {

    @SerializedName("created")
    private String created;

    @SerializedName("productQuestionId")
    private int productQuestionId;

    @SerializedName("productQuestion")
    private ProductQuestion productQuestion;

    @SerializedName("id")
    private int id;

    @SerializedName("body")
    private String body;

    @SerializedName("disLikes")
    private int disLikes;

    @SerializedName("likes")
    private int likes;

    @SerializedName("status")
    private String status;

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreated() {
        return created;
    }

    public void setProductQuestionId(int productQuestionId) {
        this.productQuestionId = productQuestionId;
    }

    public int getProductQuestionId() {
        return productQuestionId;
    }

    public void setProductQuestion(ProductQuestion productQuestion) {
        this.productQuestion = productQuestion;
    }

    public ProductQuestion getProductQuestion() {
        return productQuestion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setDisLikes(int disLikes) {
        this.disLikes = disLikes;
    }

    public int getDisLikes() {
        return disLikes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return
                "ResponseBody{" +
                        "created = '" + created + '\'' +
                        ",productQuestionId = '" + productQuestionId + '\'' +
                        ",productQuestion = '" + productQuestion + '\'' +
                        ",id = '" + id + '\'' +
                        ",body = '" + body + '\'' +
                        ",disLikes = '" + disLikes + '\'' +
                        ",likes = '" + likes + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}