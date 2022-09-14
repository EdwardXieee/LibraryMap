package com.example.librarymap.controller;

import com.example.librarymap.config.JSONResult;
import com.example.librarymap.config.PagedResult;
import com.example.librarymap.mapper.FacilityMapper;
import com.example.librarymap.service.FacilityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "设施相关接口", tags = { "Facility-Controller" })
@RequestMapping("/facility")
public class FacilityController {
    @Autowired
    FacilityMapper facilityMapper;
    @Autowired
    FacilityService facilityService;

    @ApiOperation(value = "通过单个设施的id来获取此设施的所有信息", notes = "通过单个设施的id来获取此设施的所有信息的接口")
    @PostMapping("/getFacilityById")
    public JSONResult getFacilityById(String id) {
        return JSONResult.ok(facilityMapper.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "根据选择的tag来获取包含此tag的设施", notes = "根据选择的tag来获取包含此tag的设施的接口")
    @ResponseBody
    @PostMapping("/getFacilitiesByTag()")
    public JSONResult getFacilitiesByTag(Integer page, Integer pageSize, String tag, Integer floorNum) {

        if (page == null) {
            page = 1;
        }

        PagedResult result = facilityService.getFacilitiesByTag(page, pageSize, tag, floorNum);
        return JSONResult.ok(result);
    }

    @ApiOperation(value = "通过关键词对设施进行搜索", notes = "通过关键词对设施进行搜索的接口")
    @PostMapping("/searchFacilityByKeyWords()")
    public JSONResult searchFacilityByKeyWords(Integer isSaveRecord, Integer page, Integer pageSize, String searchText, Integer floorNum) {

        if (page == null) {
            page = 1;
        }

        PagedResult result = facilityService.searchFacilityByKeyWords(isSaveRecord, page, pageSize, searchText, floorNum);

        return JSONResult.ok(result);
    }


}
