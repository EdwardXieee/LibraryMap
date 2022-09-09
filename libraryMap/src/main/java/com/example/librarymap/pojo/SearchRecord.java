package com.example.librarymap.pojo;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class SearchRecord {
    @Id
    private String id;

    // 内容
    private String content;
}
