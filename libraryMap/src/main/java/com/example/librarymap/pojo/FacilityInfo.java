package com.example.librarymap.pojo;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class FacilityInfo {
    @Id
    private String id;

    // 设施分类中文
    private String titleCn;

    // 设施分类英文
    private String titleEn;

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

    // 状态
    private Integer status;

    // 创建时间
    private Date createDate;
}
