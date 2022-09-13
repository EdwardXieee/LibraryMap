package com.example.librarymap.service;

import com.example.librarymap.config.PagedResult;
import com.example.librarymap.mapper.FacilityMapper;
import com.example.librarymap.pojo.FacilityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService{
    @Autowired
    FacilityMapper facilityMapper;

    @Override
    public List<FacilityInfo> getFacilitiesByTag(Integer page, Integer pageSize, String tag, Integer floorNum){
        Example facilityExample = new Example(FacilityInfo.class);

//        // 在这些文章中找到符合搜索的标签的设施
//        String[] texts = tag.split(" ");
//        Example.Criteria tagCriteria = facilityExample.createCriteria();
//        for (String text : texts) {
//            tagCriteria.orLike("tags", "#" + text + "#");
//        }
//        facilityExample.and(tagCriteria);

        // 在这些设施中找到楼层数为floorNum的设施
        Example.Criteria facilityCriteria = facilityExample.createCriteria();
        facilityCriteria.andEqualTo("floorNum", floorNum);
        facilityExample.and(facilityCriteria);

        // 在这些文章中找到状态为可读的文章
        Example.Criteria statusCriteria = facilityExample.createCriteria();
        statusCriteria.andEqualTo("status", 1);
        facilityExample.and(statusCriteria);

        List<FacilityInfo> list = facilityMapper.selectByExample(facilityExample);

        return list;
    }

}
