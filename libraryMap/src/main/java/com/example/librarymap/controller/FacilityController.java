package com.example.librarymap.controller;

import com.example.librarymap.config.JSONResult;
import com.example.librarymap.mapper.FacilityMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "设施相关接口", tags = { "Facility-Controller" })
@RequestMapping("/facility")
public class FacilityController {
    @Autowired
    FacilityMapper facilityMapper;

    @ApiOperation(value = "通过单个设施的id来获取此设施的所有信息", notes = "通过单个设施的id来获取此设施的所有信息的接口")
    @PostMapping("/getFacilityById")
    public JSONResult getFacilityById(String id) {
        return JSONResult.ok(facilityMapper.getFacilityById(id));
    }

    @ApiOperation(value = "根据选择的tag来获取包含此tag的设施", notes = "根据选择的tag来获取包含此tag的设施的接口")
    @PostMapping("/getFacilitiesByTag()")
    public JSONResult getFacilitiesByTag(String tag, Integer floorNum) {

        return JSONResult.ok();
    }

    @ApiOperation(value = "通过关键词对设施进行搜索", notes = "通过关键词对设施进行搜索的接口")
    @PostMapping("/searchFacilityByKeyWords()")
    public JSONResult searchFacilityByKeyWords(String searchText, Integer floorNum) {

        return JSONResult.ok();
    }


}
