package com.example.librarymap.pojo.vo;

import java.util.Date;

public class FacilityVO {
    private String id;

    // 设施分类中文
    private String titleCn;

    // 设施分类英文
    private String titleEn;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    // 设施分类
    private Integer category;

    // 设施名称中文
    private String nameCn;

    // 设施名称英文
    private String nameEn;

    // 设施介绍中文
    private String descriptionCn;

    // 设施介绍英文
    private String descriptionEn;

    // 楼层数
    private Integer floorNum;

    // 检索条目
    private String contentForSearch;

    // 设施图片
    private String imgPath;

    // 状态
    private Integer status;

    // 创建时间
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleCn() {
        return titleCn;
    }

    public void setTitleCn(String titleCn) {
        this.titleCn = titleCn;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getDescriptionCn() {
        return descriptionCn;
    }

    public void setDescriptionCn(String descriptionCn) {
        this.descriptionCn = descriptionCn;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public Integer getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }

    public String getContentForSearch() {
        return contentForSearch;
    }

    public void setContentForSearch(String contentForSearch) {
        this.contentForSearch = contentForSearch;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
