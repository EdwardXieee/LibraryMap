package com.example.librarymap.service;

import com.example.librarymap.config.PagedResult;
import com.example.librarymap.pojo.vo.FacilityVO;
import org.springframework.stereotype.Service;

@Service
public interface FacilityService {

    FacilityVO getFacilityById(String facilityId);

    PagedResult searchFacilityByKeyWordsOrTag(Boolean isSaveRecord, Integer page, Integer pageSize, String searchText, Integer floorNum);
}
