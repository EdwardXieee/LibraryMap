package com.example.librarymap.controller;

import com.example.librarymap.config.JSONResult;
import com.example.librarymap.config.PagedResult;
import com.example.librarymap.pojo.vo.FacilityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "设施相关接口", tags = { "Facility-Controller" })
@RequestMapping("/facility")
public class FacilityController extends BasicController{

    @ApiOperation(value = "通过单个设施的id来获取此设施的所有信息", notes = "通过单个设施的id来获取此设施的所有信息的接口")
    @PostMapping("/getFacilityById")
    public JSONResult getFacilityById(String id) {

        if (id.isEmpty()){
            JSONResult.errorMsg("Id cannot be null");
        }

        FacilityVO result = facilityService.getFacilityById(id);

        return JSONResult.ok(result);
    }


    @ApiOperation(value = "通过关键词对设施进行搜索", notes = "通过关键词对设施进行搜索的接口")
    @PostMapping("/searchFacilityByKeyWordsOrTag")
    public JSONResult searchFacilityByKeyWordsOrTag(Boolean isSaveRecord, Integer page, Integer pageSize, String searchText, Integer floorNum) {

        if (page == null) {
            page = 1;
        }

        PagedResult result = facilityService.searchFacilityByKeyWordsOrTag(isSaveRecord, page, pageSize, searchText, floorNum);

        return JSONResult.ok(result);
    }


}
