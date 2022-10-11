package com.example.librarymap.service;

import com.example.librarymap.config.PagedResult;
import com.example.librarymap.pojo.FacilityInfo;
import com.example.librarymap.pojo.vo.FacilityVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FacilityService {

    List<FacilityVO> getAllFacilities();

    FacilityVO getFacilityById(String facilityId);

    PagedResult searchFacilityByKeyWordsOrTag(Boolean isSaveRecord, Integer page, Integer pageSize, String searchText, Integer floorNum);

    String saveFacility(FacilityInfo facilityInfo, String id);
}
