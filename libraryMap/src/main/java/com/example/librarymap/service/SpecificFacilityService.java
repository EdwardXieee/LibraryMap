package com.example.librarymap.service;

import com.example.librarymap.config.PagedResult;
import com.example.librarymap.enums.SpecificFacilityPostType;
import com.example.librarymap.pojo.SpecificFacilityInfo;
import com.example.librarymap.pojo.vo.SpecificFacilityVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpecificFacilityService {
    List<SpecificFacilityVO> getAllSpecificFacilities();

    SpecificFacilityVO getFacilityVOById(String specificFacilityId);

    PagedResult searchSpecificFacilityByKeyWordsOrTag(Boolean isSaveRecord, Integer page, Integer pageSize, String searchText, Integer floorNum);

    String saveSpecificFacility(SpecificFacilityInfo specificFacilityInfo, String id);

    int deleteSpecificFacility(String specificFacilityId);

    // 用于修改facility的信息
    int modifySpecificFacilityInfo(String specificFacilityId, SpecificFacilityPostType targetAttribute, String value);
}
