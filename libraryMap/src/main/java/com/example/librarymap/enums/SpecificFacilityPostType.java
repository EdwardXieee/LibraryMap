package com.example.librarymap.enums;

public enum SpecificFacilityPostType {
    FACILITYID("facilityId"),    // 外键id
    NAMECN("nameCn"),			// 设施名称中文
    NAMEEN("nameEn"),			// 设施名称英文
    FLOORNUM("floorNum"),					// 楼层数
    CONTENTFORSEARCH("contentForSearch");			// 搜索内容

    public final String value;

    SpecificFacilityPostType(String value){
        this.value = value;
    }

    public String getValue() {return value; }
}
