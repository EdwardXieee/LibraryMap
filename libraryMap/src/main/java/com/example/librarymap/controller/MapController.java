package com.example.librarymap.controller;

import com.example.librarymap.config.JSONResult;
import com.example.librarymap.mapper.FacilityMapper;
import com.example.librarymap.mapper.LibraryMapMapper;
import com.example.librarymap.pojo.FacilityInfo;
import com.example.librarymap.pojo.LibraryMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RestController
@Api(value = "地图相关接口", tags = { "Map-Controller" })
@RequestMapping("/map")
public class MapController {
    @Autowired
    private FacilityMapper facilityMapper;
    @Autowired
    private LibraryMapMapper libraryMapMapper;

    @ApiOperation(value = "获取所有楼层的地图", notes = "获取所有楼层的地图的接口")
    @PostMapping("/queryMaps")
    public JSONResult queryMaps() {
        return JSONResult.ok(libraryMapMapper.selectAll());
    }

    @ApiOperation(value = "根据楼层数获取地图", notes = "根据楼层数获取地图的接口")
    @PostMapping("/getMapByFloor")
    public JSONResult getMapByFloor(Integer floorNum) {
        Example mapExample = new Example(LibraryMap.class);
        Example.Criteria floorNumCriteria = mapExample.createCriteria();
        floorNumCriteria.andEqualTo("floorNum", floorNum);
        LibraryMap result = libraryMapMapper.selectOneByExample(mapExample);
        return JSONResult.ok(result);
    }
}
