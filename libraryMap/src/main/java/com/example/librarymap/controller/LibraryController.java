package com.example.librarymap.controller;

import com.example.librarymap.mapper.FacilityMapper;
import com.example.librarymap.pojo.FacilityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private FacilityMapper facilityMapper;

    @GetMapping("/findAll")
    public List<FacilityInfo> findAll() {
        return facilityMapper.findAll();
    }
}
