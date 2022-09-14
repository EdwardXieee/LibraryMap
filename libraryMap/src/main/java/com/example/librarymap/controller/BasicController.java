package com.example.librarymap.controller;

import com.example.librarymap.service.FacilityService;
import com.example.librarymap.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;

public class BasicController {
    @Autowired
    public FacilityService facilityService;
    @Autowired
    public MapService mapService;
}
