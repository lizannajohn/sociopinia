package com.example.sociopinia.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SentimentAnalysisResponse {

    private String type;
    private double score;
    private double ratio;
    private List<Keyword> keywords;
    private String version;
    private String author;
    private String email;
    @SerializedName("result_code")
    private String resultCode;
    @SerializedName("result_msg")
    private String resultMsg;

    static class Keyword {

        private String score;
        private String word;

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public double getTypePercentage() {
        // Calculate percentage from [-1;1]
        return ((score + 1) / 2) * 100;
    }

    @Override
    public String toString() {
        return "SentimentAnalysisResponse{" +
                "type='" + type + '\'' +
                ", score=" + score +
                ", ratio=" + ratio +
                ", keywords=" + keywords +
                ", version='" + version + '\'' +
                ", author='" + author + '\'' +
                ", email='" + email + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                '}';
    }
}
