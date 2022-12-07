package com.example.librarymap.pojo;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class SpecificFacilityInfo {
    @Id
    private String id;

    // 对应设施大类的id
    private String facilityId;

    // 设施名称中文
    private String nameCn;

    // 设施名称英文
    private String nameEn;

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
}
