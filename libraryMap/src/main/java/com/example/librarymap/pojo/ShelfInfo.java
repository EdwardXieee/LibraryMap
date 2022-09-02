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

    private String facilityId;

    private String categoryCn;

    private String categoryEn;

    private String popularityCn;

    private String popularityEn;

    private Integer status;

    private Date createDate;
}
