package com.example.librarymap.service;

import com.example.librarymap.config.PagedResult;
import com.example.librarymap.mapper.FacilityMapper;
import com.example.librarymap.mapper.SearchRecordMapper;
import com.example.librarymap.pojo.FacilityInfo;
import com.example.librarymap.pojo.vo.FacilityVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService{

    static Integer ID=1;//暂时使用的ID，未使用sid

//    public FacilityServiceImpl(){
//        ID++;
//    }

    @Autowired
    FacilityMapper facilityMapper;

    @Autowired
    SearchRecordMapper searchRecordMapper;

    public FacilityVO composeFacilityVO(FacilityInfo facilityInfo) {
        FacilityVO facilityVO = new FacilityVO();
        BeanUtils.copyProperties(facilityInfo, facilityVO);
        switch (facilityInfo.getCategory()){
            case 1:
                facilityVO.setTitleCn("书籍类设施");
                facilityVO.setTitleEn("");
                break;
            case 2:
                facilityVO.setTitleCn("自习类设施");
                facilityVO.setTitleEn("");
                break;
            case 3:
                facilityVO.setTitleCn("小组类设施");
                facilityVO.setTitleEn("");
                break;
            case 4:
                facilityVO.setTitleCn("多功能类设施");
                facilityVO.setTitleEn("");
                break;
            case 5:
                facilityVO.setTitleCn("基础设施");
                facilityVO.setTitleEn("");
                break;
            case 6:
                facilityVO.setTitleCn("特殊设施");
                facilityVO.setTitleEn("");
                break;
        }
        return facilityVO;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<FacilityVO> getAllFacilities(){
        List<FacilityInfo> list = facilityMapper.selectAll();
        List<FacilityVO> newList= new ArrayList<>();
        for (FacilityInfo f : list){
            newList.add(composeFacilityVO(f));
        }
        return newList;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public FacilityVO getFacilityById(String facilityId){
        FacilityInfo facilityInfo = facilityMapper.selectByPrimaryKey(facilityId);
        return composeFacilityVO(facilityInfo);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedResult queryFacilitiesByExample(Example facilityExample) {
        // 通过条件，返回pagedResult
        List<FacilityInfo> list = facilityMapper.selectByExample(facilityExample);
        PageInfo<FacilityInfo> pageInfo = new PageInfo<>(list);

        // 为最终返回对象 pagedResult 添加属性
        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(pageInfo.getPageNum());
        pagedResult.setTotal(pageInfo.getPages());
        pagedResult.setRows(pageInfo.getList());
        pagedResult.setRecords(pageInfo.getTotal());

        return pagedResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult searchFacilityByKeyWordsOrTag(Boolean isSaveRecord, Integer page, Integer pageSize, String searchText, Integer floorNum){
        String[] texts = searchText.split(" ");

        // 开启分页查询并转换为vo对象
        // 在Example中的每一个Criteria相当于一个括号，把里面的内容当成一个整体
        Example facilityExample = new Example(FacilityInfo.class);
        facilityExample.setOrderByClause("create_date desc");

        Example.Criteria criteria = facilityExample.createCriteria();
        for (String text : texts) {
            criteria.orLike("nameCn", "%" + text + "%");
            criteria.orLike("nameEn", "%" + text + "%");
            criteria.orLike("titleCn", "%" + text + "%");
            criteria.orLike("titleEn", "%" + text + "%");
            criteria.orLike("descriptionCn", "%" + text + "%");
            criteria.orLike("descriptionEn", "%" + text + "%");
            criteria.orLike("contentForSearch", "%" + text + "%");
        }


        Example.Criteria floorNumCriteria = facilityExample.createCriteria();
        floorNumCriteria.andEqualTo("floorNum", floorNum);

        Example.Criteria statusCriteria = facilityExample.createCriteria();
        statusCriteria.andEqualTo("status", "1");

        facilityExample.and(criteria);
        facilityExample.and(floorNumCriteria);
        facilityExample.and(statusCriteria);

        return queryFacilitiesByExample(facilityExample);
    }

    @Override
    public String saveFacility(FacilityInfo facilityInfo) {
        ID++;//暂时使用的ID自增方式
        String id = ID.toString();
        facilityInfo.setId(id);
        facilityMapper.insertSelective(facilityInfo);
        return id;
    }

}
