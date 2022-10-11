package com.example.librarymap.controller;

import com.example.librarymap.config.JSONResult;
import com.example.librarymap.config.PagedResult;
import com.example.librarymap.pojo.FacilityInfo;
import com.example.librarymap.pojo.LibraryMap;
import com.example.librarymap.pojo.vo.FacilityVO;
import com.example.librarymap.service.FacilityService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@Api(value = "设施相关接口", tags = { "Facility-Controller" })
@RequestMapping("/facility")
public class FacilityController extends BasicController{

    @ApiOperation(value = "查询所有设施", notes = "查询所有设施的接口")
    @PostMapping("/queryFacilities")
    public JSONResult queryFacilities() {
        List<FacilityVO> result = facilityService.getAllFacilities();
        return JSONResult.ok(result);
    }

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


    @ApiOperation(value = "上传或修改设施信息", notes = "上传或修改设施信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="category", value="设施分类", required=true, dataType="Integer", paramType="form"),
            @ApiImplicitParam(name="nameCn", value="设施名称中文", required=true, dataType="String", paramType="form"),
            @ApiImplicitParam(name="nameEn", value="设施名称英文", required=false, dataType="String", paramType="form"),
            @ApiImplicitParam(name="descriptionCn", value="设施介绍中文", required=false, dataType="String", paramType="form"),
            @ApiImplicitParam(name="descriptionEn", value="设施介绍英文", required=false, dataType="String", paramType="form"),
            @ApiImplicitParam(name="floorNum", value="楼层数", required=true, dataType="Integer", paramType="form"),
            @ApiImplicitParam(name="contentForSearch", value="用于检索的条目(默认包含类别和名称e.g., 卫生间#wc#厕所#toilet#茅坑)", required=true, dataType="String", paramType="form")
    })
    @PostMapping(value="/uploadFacility")
    public JSONResult uploadFacility(@ApiParam(value = "file", required = false) MultipartFile img,
                                     Integer category,
                                     String nameCn,
                                     String nameEn,
                                     String descriptionCn,
                                     String descriptionEn,
                                     Integer floorNum,
                                     String contentForSearch) throws Exception{
        FacilityInfo facilityInfo = new FacilityInfo();

        //上传设施图片
//        if (img != null && (status==1 || status == 2)){
            //判断大小是否超出限制
//            if (img.getSize() > MAX_IMAGE_SIZE) {
//                return JSONResult.errorException("Uploaded file size exceed server's limit (10MB)");
//            }
//            String imgName = img.getOriginalFilename();
//            if (StringUtils.isNotBlank(imgName)) {
//                // 保存到数据库中的相对路径
////                String uploadPathDB = resourceService.uploadImg(img);
//            }else {
//                return JSONResult.errorMsg("File name is blank");
//            }
//        }else {
//            return JSONResult.errorMsg("Upload error");
//        }
        facilityInfo.setCategory(category);
        facilityInfo.setNameCn(nameCn);
        facilityInfo.setNameEn(nameEn);
        facilityInfo.setDescriptionCn(descriptionCn);
        facilityInfo.setDescriptionEn(descriptionEn);
        facilityInfo.setFloorNum(floorNum);
        facilityInfo.setContentForSearch(contentForSearch);

        String facilityVOId = facilityService.saveFacility(facilityInfo); // 存入数据库

        return JSONResult.ok(facilityVOId);
    }
}
