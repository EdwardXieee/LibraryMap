package com.example.librarymap.pojo.vo;

import java.util.Date;

public class ShelfInfoVO {
    private String id;

    // 设施Id
    private String facilityId;

    // 分类中文
    private String categoryCn;

    // 分类英文
    private String categoryEn;

    // 热度中文
    private String popularityCn;

    // 热度英文
    private String popularityEn;

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

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getCategoryCn() {
        return categoryCn;
    }

    public void setCategoryCn(String categoryCn) {
        this.categoryCn = categoryCn;
    }

    public String getCategoryEn() {
        return categoryEn;
    }

    public void setCategoryEn(String categoryEn) {
        this.categoryEn = categoryEn;
    }

    public String getPopularityCn() {
        return popularityCn;
    }

    public void setPopularityCn(String popularityCn) {
        this.popularityCn = popularityCn;
    }

    public String getPopularityEn() {
        return popularityEn;
    }

    public void setPopularityEn(String popularityEn) {
        this.popularityEn = popularityEn;
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
