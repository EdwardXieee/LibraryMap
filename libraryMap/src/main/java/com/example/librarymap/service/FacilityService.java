package com.example.librarymap.service;

import com.example.librarymap.config.PagedResult;
import org.springframework.stereotype.Service;

@Service
public interface FacilityService {
    PagedResult getFacilitiesByTag(Integer page, Integer pageSize, String tag, Integer floorNum);

    PagedResult searchFacilityByKeyWords(Integer isSaveRecord, Integer page, Integer pageSize, String searchText, Integer floorNum);
}
