package com.example.librarymap.controller;

import com.example.librarymap.config.JSONResult;
import com.example.librarymap.pojo.LibraryMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "地图相关接口", tags = { "Map-Controller" })
@RequestMapping("/map")
public class MapController extends BasicController{

    @ApiOperation(value = "获取所有楼层的地图", notes = "获取所有楼层的地图的接口")
    @PostMapping("/queryMaps")
    public JSONResult queryMaps() {
        List<LibraryMap> list = mapService.getAllLibraryMap();
        return JSONResult.ok(list);
    }

    @ApiOperation(value = "根据楼层数获取地图", notes = "根据楼层数获取地图的接口")
    @PostMapping("/getMapByFloor")
    public JSONResult getMapByFloor(Integer floorNum) {
        if (floorNum<1 || floorNum>4){
            JSONResult.errorException("Invalid floor number");
        }
        LibraryMap result = mapService.getLibraryMapByFloor(floorNum);
        return JSONResult.ok(result);
    }
}
