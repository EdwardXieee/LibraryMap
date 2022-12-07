package com.example.librarymap.service;

import com.example.librarymap.config.PagedResult;
import com.example.librarymap.enums.FacilityPostType;
import com.example.librarymap.enums.SpecificFacilityPostType;
import com.example.librarymap.mapper.FacilityMapper;
import com.example.librarymap.mapper.SpecificFacilityMapper;
import com.example.librarymap.pojo.FacilityInfo;
import com.example.librarymap.pojo.SpecificFacilityInfo;
import com.example.librarymap.pojo.vo.FacilityVO;
import com.example.librarymap.pojo.vo.SpecificFacilityVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

public class SpecificFacilityServiceImpl implements SpecificFacilityService{

    @Autowired
    SpecificFacilityMapper specificFacilityMapper;
    @Autowired
    FacilityMapper facilityMapper;

    public SpecificFacilityVO composeSpecificFacilityVO(SpecificFacilityInfo specificFacilityInfo) {
        SpecificFacilityVO specificFacilityVO = new SpecificFacilityVO();
        BeanUtils.copyProperties(specificFacilityInfo, specificFacilityVO);
        specificFacilityVO.setDescriptionCn(facilityMapper.selectByPrimaryKey(specificFacilityInfo.getFacilityId()).getDescriptionCn());
        specificFacilityVO.setDescriptionEn(facilityMapper.selectByPrimaryKey(specificFacilityInfo.getFacilityId()).getDescriptionEn());
        return specificFacilityVO;
    }

    @Override
    public List<SpecificFacilityVO> getAllSpecificFacilities() {
        List<SpecificFacilityInfo> list = specificFacilityMapper.selectAll();
        // 添加VO属性
        List<SpecificFacilityVO> newList = new ArrayList<>();
        for (SpecificFacilityInfo f : list) {
            newList.add(composeSpecificFacilityVO(f));
        }
        return newList;
    }

    @Override
    public SpecificFacilityVO getFacilityVOById(String specificFacilityId) {
        SpecificFacilityInfo specificFacilityInfo = specificFacilityMapper.selectByPrimaryKey(specificFacilityId);
        return composeSpecificFacilityVO(specificFacilityInfo);
    }

    public PagedResult querySpecificFacilitiesByExample(Example specificFacilityExample) {
        // 通过条件，返回pagedResult
        List<SpecificFacilityInfo> list = specificFacilityMapper.selectByExample(specificFacilityExample);
        // 添加VO属性
        List<SpecificFacilityVO> newList = new ArrayList<>();
        for (SpecificFacilityInfo f : list) {
            newList.add(composeSpecificFacilityVO(f));
        }
        PageInfo<SpecificFacilityVO> pageInfo = new PageInfo<>(newList);

        // 为最终返回对象 pagedResult 添加属性
        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(pageInfo.getPageNum());
        pagedResult.setTotal(pageInfo.getPages());
        pagedResult.setRows(pageInfo.getList());
        pagedResult.setRecords(pageInfo.getTotal());

        return pagedResult;
    }

    @Override
    public PagedResult searchSpecificFacilityByKeyWordsOrTag(Boolean isSaveRecord, Integer page, Integer pageSize, String searchText, Integer floorNum) {
        // 开启分页查询并转换为vo对象
        // 在Example中的每一个Criteria相当于一个括号，把里面的内容当成一个整体
        Example specificFacilityExample = new Example(SpecificFacilityInfo.class);
        specificFacilityExample.setOrderByClause("floor_num desc");

        Example.Criteria criteria = specificFacilityExample.createCriteria();

        criteria.orLike("nameCn", "%" + searchText + "%");
        criteria.orLike("nameEn", "%" + searchText + "%");
        criteria.orLike("contentForSearch", "%" + searchText + "%");

        Example.Criteria floorNumCriteria = specificFacilityExample.createCriteria();
        floorNumCriteria.andEqualTo("floorNum", floorNum);

        Example.Criteria statusCriteria = specificFacilityExample.createCriteria();
        statusCriteria.andEqualTo("status", "1");

        specificFacilityExample.and(criteria);
        specificFacilityExample.and(floorNumCriteria);
        specificFacilityExample.and(statusCriteria);

        return querySpecificFacilitiesByExample(specificFacilityExample);
    }

    @Override
    public String saveSpecificFacility(SpecificFacilityInfo specificFacilityInfo, String id) {
        //TODO: id目前为手动输入，之后考虑自动创建
        specificFacilityInfo.setId(id);
        specificFacilityMapper.insertSelective(specificFacilityInfo);
        return id;
    }

    @Override
    public int deleteSpecificFacility(String specificFacilityId) {
        return specificFacilityMapper.deleteByPrimaryKey(specificFacilityId);
    }

    @Override
    public int modifySpecificFacilityInfo(String specificFacilityId, SpecificFacilityPostType targetAttribute, String value) {
        SpecificFacilityInfo specificFacilityInfo = specificFacilityMapper.selectByPrimaryKey(specificFacilityId);
        switch (targetAttribute) {
            case FACILITYID:
                specificFacilityInfo.setFacilityId(value);
                break;
            case NAMECN:
                specificFacilityInfo.setNameCn(value);
                break;
            case NAMEEN:
                specificFacilityInfo.setNameEn(value);
                break;
            case FLOORNUM:
                int floorNum = Integer.parseInt(value);
                specificFacilityInfo.setFloorNum(floorNum);
                break;
            case CONTENTFORSEARCH:
                specificFacilityInfo.setContentForSearch(value);
                break;
        }
        return specificFacilityMapper.updateByPrimaryKey(specificFacilityInfo);
    }
}
