package com.example.demo.entity;

public class Goods {
    private Integer goodsId;

    private String goodsStorename;

    private String goodsName;

    private String goodsType;

    private String goodsPrice;

    private String goodsSize;

    private String goodsJpg;

    private Integer goodsBargin;

    private Integer goodsNumber;

    private Integer goodsNew;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsStorename() {
        return goodsStorename;
    }

    public void setGoodsStorename(String goodsStorename) {
        this.goodsStorename = goodsStorename == null ? null : goodsStorename.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType == null ? null : goodsType.trim();
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice == null ? null : goodsPrice.trim();
    }

    public String getGoodsSize() {
        return goodsSize;
    }

    public void setGoodsSize(String goodsSize) {
        this.goodsSize = goodsSize == null ? null : goodsSize.trim();
    }

    public String getGoodsJpg() {
        return goodsJpg;
    }

    public void setGoodsJpg(String goodsJpg) {
        this.goodsJpg = goodsJpg == null ? null : goodsJpg.trim();
    }

    public Integer getGoodsBargin() {
        return goodsBargin;
    }

    public void setGoodsBargin(Integer goodsBargin) {
        this.goodsBargin = goodsBargin;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Integer getGoodsNew() {
        return goodsNew;
    }

    public void setGoodsNew(Integer goodsNew) {
        this.goodsNew = goodsNew;
    }
}