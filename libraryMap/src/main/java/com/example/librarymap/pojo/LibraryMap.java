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

    private String imgPath;

    private Integer floorNum;

    private Date createDate;
}
