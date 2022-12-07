package com.example.librarymap.controller;

import com.example.librarymap.config.JSONResult;
import com.example.librarymap.config.PagedResult;
import com.example.librarymap.enums.SpecificFacilityPostType;
import com.example.librarymap.pojo.SpecificFacilityInfo;
import com.example.librarymap.pojo.vo.SpecificFacilityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Api(value = "具体设施相关接口", tags = { "Specific-Facility-Controller" })
@RequestMapping("/specificFacility")
public class SpecificFacilityController extends BasicController{

    @ApiOperation(value = "查询所有具体设施", notes = "查询所有具体设施的接口")
    @PostMapping("/querySpecificFacilities")
    public JSONResult querySpecificFacilities() {
        List<SpecificFacilityVO> result = specificFacilityService.getAllSpecificFacilities();
        return JSONResult.ok(result);
    }

    @ApiOperation(value = "通过单个具体设施的id来获取此具体设施的所有信息", notes = "通过单个具体设施的id来获取此具体设施的所有信息的接口")
    @PostMapping("/getSpecificFacilityById")
    public JSONResult getSpecificFacilityById(@RequestParam @ApiParam(required = true) String id) {

        if (id.isEmpty()){
            JSONResult.errorException("Id cannot be null");
        }

        SpecificFacilityVO result = specificFacilityService.getFacilityVOById(id);

        return JSONResult.ok(result);
    }

    @ApiOperation(value = "通过关键词对具体设施进行搜索", notes = "通过关键词对具体设施进行搜索的接口")
    @PostMapping("/searchSpecificFacilityByKeyWordsOrTag")
    public JSONResult searchSpecificFacilityByKeyWordsOrTag(Boolean isSaveRecord,
                                                    Integer page, Integer pageSize,
                                                    @RequestParam @ApiParam(required = true) String searchText,
                                                    Integer floorNum) {

        if (page == null) {
            page = 1;
        }

        PagedResult result = specificFacilityService.searchSpecificFacilityByKeyWordsOrTag(isSaveRecord, page, pageSize, searchText, floorNum);

        return JSONResult.ok(result);
    }

    @ApiOperation(value = "上传具体设施信息", notes = "上传具体设施信息的接口")
    @PostMapping(value="/uploadSpecificFacility")
    public JSONResult uploadSpecificFacility(@ApiParam(value = "file") MultipartFile img,
                                             @RequestParam @ApiParam(required = true) String id,
                                             @RequestParam @ApiParam(required = true) String facilityId,
                                             @RequestParam @ApiParam(required = true) String nameCn,
                                             String nameEn,
                                             Integer floorNum,
                                             String contentForSearch) {
        SpecificFacilityInfo specificFacilityInfo = new SpecificFacilityInfo();

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
        specificFacilityInfo.setFacilityId(facilityId);
        specificFacilityInfo.setNameCn(nameCn);
        specificFacilityInfo.setNameEn(nameEn);
        specificFacilityInfo.setFloorNum(floorNum);
        specificFacilityInfo.setContentForSearch(contentForSearch);

        String specificFacilityVOId = specificFacilityService.saveSpecificFacility(specificFacilityInfo, id); // 存入数据库

        return JSONResult.ok(specificFacilityVOId);
    }

    // 此接口为真删除，慎用！！！
    @ApiOperation(value = "删除具体设施信息（真删除，慎用！！！）", notes = "删除具体设施信息的接口")
    @PostMapping("/deleteSpecificFacilityById")
    public JSONResult deleteSpecificFacilityById(@RequestParam @ApiParam(required = true) String specificFacilityId) {
        if (specificFacilityId.isEmpty()){
            JSONResult.errorException("Id cannot be null");
        }

        if (specificFacilityService.deleteSpecificFacility(specificFacilityId) == 0)
            return JSONResult.ok("无法删除");
        else
            return JSONResult.ok("删除成功");
    }

    @ApiOperation(value = "更改具体设施信息", notes = "更改具体设施信息的接口")
    @PostMapping("/modifySpecificFacilityInfo")
    public JSONResult modifySpecificFacilityInfo(@RequestParam @ApiParam(required = true) String specificFacilityId,
                                                 @RequestParam @ApiParam(required = true) SpecificFacilityPostType targetAttribute,
                                                 @RequestParam @ApiParam(required = true) String value){
        return specificFacilityService.modifySpecificFacilityInfo(specificFacilityId, targetAttribute, value) > 0 ?
                JSONResult.ok("修改成功") : JSONResult.errorMsg("修改失败");
    }
}
