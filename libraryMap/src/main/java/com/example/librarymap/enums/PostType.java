package com.example.librarymap.enums;

public enum PostType {

    CATEGORY("category"),     // 设施分类
    NAMECN("nameCn"),			// 设施名称中文
    NAMEEN("nameEn"),			// 设施名称英文
    DESCRIPTIONCN("descriptionCn"),	// 设施介绍中文
    DESCRIPTIONEN("descriptionEn"),					// 设施介绍英文
    FLOORNUM("floorNum"),					// 楼层数
    CONTENTFORSEARCH("contentForSearch");			// 楼层数

    public final String value;

    PostType(String value){
        this.value = value;
    }

    public String getValue() {return value; }


}
