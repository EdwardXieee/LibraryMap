package com.example.librarymap.pojo;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class LibraryMap {
    @Id
    private String id;

    // 地图图片地址
    private String imgPath;

    // 楼层数
    private Integer floorNum;

    // 创建时间
    private Date createDate;
}
