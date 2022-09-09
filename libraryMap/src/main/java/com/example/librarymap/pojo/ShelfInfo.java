package com.example.librarymap.pojo;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class ShelfInfo {
    @Id
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
}
