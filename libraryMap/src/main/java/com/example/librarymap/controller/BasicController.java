package com.example.librarymap.controller;

import com.example.librarymap.service.FacilityService;
import com.example.librarymap.service.MapService;
import com.example.librarymap.service.SpecificFacilityService;
import org.springframework.beans.factory.annotation.Autowired;

public class BasicController {
    @Autowired
    public SpecificFacilityService specificFacilityService;
    @Autowired
    public FacilityService facilityService;
    @Autowired
    public MapService mapService;
    public long MAX_IMAGE_SIZE;
}
