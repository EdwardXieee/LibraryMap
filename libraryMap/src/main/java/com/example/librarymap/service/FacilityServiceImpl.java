package com.example.librarymap.service;

import com.example.librarymap.config.PagedResult;
import com.example.librarymap.mapper.FacilityMapper;
import com.example.librarymap.mapper.SearchRecordMapper;
import com.example.librarymap.pojo.FacilityInfo;
import com.example.librarymap.pojo.vo.FacilityVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;
import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService{
    @Autowired
    FacilityMapper facilityMapper;

    @Autowired
    SearchRecordMapper searchRecordMapper;

    public FacilityVO composeFacilityVO(FacilityInfo facilityInfo) {
        FacilityVO facilityVO = new FacilityVO();
        BeanUtils.copyProperties(facilityInfo, facilityVO);
        return facilityVO;
    }

    @Override
    public PagedResult getFacilitiesByTag(Integer page, Integer pageSize, String tag, Integer floorNum){
        Example facilityExample = new Example(FacilityInfo.class);
        // 是否关注的, queryType: 0为全部，1为关注
        // 查询全部我(操作者)关注的用户
        // 是否有标签
        if (!StringUtil.isEmpty(tag)) {
            // 在这些文章中找到符合搜索的标签的文章
            String[] texts = tag.split(" ");
            Example.Criteria tagCriteria = facilityExample.createCriteria();
            for (String text : texts) {
                tagCriteria.orLike("contentForSearch", "%" + text + "%");
            }
            facilityExample.and(tagCriteria);
        }
        // 在这些文章中找到状态为可读的文章
        Example.Criteria statusCriteria = facilityExample.createCriteria();
        statusCriteria.andEqualTo("status", 1);

        Example.Criteria floorNumCriteria = facilityExample.createCriteria();
        statusCriteria.andEqualTo("floorNum", floorNum);

        facilityExample.and(statusCriteria);
        facilityExample.and(floorNumCriteria);
        facilityExample.setOrderByClause("create_date desc");

        PageHelper.startPage(page, pageSize);
        return queryFacilitiesByExample(facilityExample);
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
    public PagedResult searchFacilityByKeyWords(Integer isSaveRecord, Integer page, Integer pageSize, String searchText, Integer floorNum){
        String[] texts = searchText.split(" ");

        // 开启分页查询并转换为vo对象
        // 在Example中的每一个Criteria相当于一个括号，把里面的内容当成一个整体
        Example articleExample = new Example(FacilityInfo.class);
        articleExample.setOrderByClause("create_date desc");

        Example.Criteria criteria = articleExample.createCriteria();
        for (String text : texts) {
            criteria.orLike("nameCn", "%" + text + "%");
        }

        Example.Criteria criteria2 = articleExample.createCriteria();
        for (String text : texts) {
            criteria.orLike("nameEn", "%" + text + "%");
        }

        Example.Criteria criteria3 = articleExample.createCriteria();
        criteria3.andEqualTo("status", "1");

        articleExample.and(criteria2);
        articleExample.and(criteria3);

        List<FacilityInfo> list = facilityMapper.selectByExample(articleExample);
        PageInfo<FacilityInfo> pageInfo = new PageInfo<>(list);

        // 为最终返回对象 pagedResult 添加属性
        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(pageInfo.getPageNum());
        pagedResult.setTotal(pageInfo.getPages());
        pagedResult.setRows(pageInfo.getList());
        pagedResult.setRecords(pageInfo.getTotal());

        return pagedResult;
    }

}
