package com.example.librarymap.controller;

import com.example.librarymap.config.JSONResult;
import com.example.librarymap.config.PagedResult;
import com.example.librarymap.enums.FacilityPostType;
import com.example.librarymap.pojo.FacilityInfo;
import com.example.librarymap.pojo.vo.FacilityVO;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
    public JSONResult getFacilityById(@RequestParam @ApiParam(required = true) String id) {

        if (id.isEmpty()){
            JSONResult.errorException("Id cannot be null");
        }

        FacilityVO result = facilityService.getFacilityVOById(id);

        return JSONResult.ok(result);
    }


    @ApiOperation(value = "通过关键词对设施进行搜索", notes = "通过关键词对设施进行搜索的接口")
    @PostMapping("/searchFacilityByKeyWordsOrTag")
    public JSONResult searchFacilityByKeyWordsOrTag(Boolean isSaveRecord,
                                                    Integer page, Integer pageSize,
                                                    @RequestParam @ApiParam(required = true) String searchText,
                                                    Integer floorNum) {

        if (page == null) {
            page = 1;
        }

        PagedResult result = facilityService.searchFacilityByKeyWordsOrTag(isSaveRecord, page, pageSize, searchText, floorNum);

        return JSONResult.ok(result);
    }


    @ApiOperation(value = "上传设施信息", notes = "上传设施信息的接口")
    @PostMapping(value="/uploadFacility")
    public JSONResult uploadFacility(@ApiParam(value = "file", required = false) MultipartFile img,
                                     @RequestParam @ApiParam(required = true) String id,
                                     @RequestParam @ApiParam(required = true) Integer category,
                                     @RequestParam @ApiParam(required = true) String nameCn,
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

        String facilityVOId = facilityService.saveFacility(facilityInfo, id); // 存入数据库

        return JSONResult.ok(facilityVOId);
    }

    // 此接口为真删除，慎用！！！
    @ApiOperation(value = "删除设施信息（真删除，慎用！！！）", notes = "删除设施信息的接口")
    @PostMapping("/deleteFacilityById")
    public JSONResult deleteFacilityById(@RequestParam @ApiParam(required = true) String facilityId) {
        if (facilityId.isEmpty()){
            JSONResult.errorException("Id cannot be null");
        }

        if (facilityService.deleteFacility(facilityId) == 0)
            return JSONResult.ok("无法删除");
        else
            return JSONResult.ok("删除成功");
    }

    @ApiOperation(value = "更改设施信息", notes = "更改设施信息的接口")
    @PostMapping("/modifyFacilityInfo")
    public JSONResult modifyFacilityInfo(@RequestParam @ApiParam(required = true) String facilityId,
                                         @RequestParam @ApiParam(required = true) FacilityPostType targetAttribute,
                                         @RequestParam @ApiParam(required = true) String value){
        return facilityService.modifyFacilityInfo(facilityId, targetAttribute, value) > 0 ?
                JSONResult.ok("修改成功") : JSONResult.errorMsg("修改失败");
    }

}
