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

    private String titleCn;

    private String titleEn;

    private String nameCn;

    private String nameEn;

    private String descriptionCn;

    private String descriptionEn;

    private Integer floorNum;

    private String contentForSearch;

    private Integer status;

    private Date createDate;
}
