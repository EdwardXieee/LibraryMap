package com.example.librarymap.service;

import com.example.librarymap.config.PagedResult;
import com.example.librarymap.pojo.FacilityInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FacilityService {
    public PagedResult getFacilitiesByTag(Integer page, Integer pageSize, String tag, Integer floorNum);
}
