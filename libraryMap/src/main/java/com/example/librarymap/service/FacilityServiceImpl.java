package com.example.librarymap.service;

import com.example.librarymap.config.PagedResult;
import com.example.librarymap.mapper.FacilityMapper;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService{
    @Autowired
    FacilityMapper facilityMapper;

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
                tagCriteria.orLike("tags", "%" + text + "%");
            }
            facilityExample.and(tagCriteria);
        }
        // 在这些文章中找到状态为可读的文章
        Example.Criteria statusCriteria = facilityExample.createCriteria();
        statusCriteria.andEqualTo("status", 1);
        facilityExample.and(statusCriteria);
        facilityExample.setOrderByClause("create_date desc");

        PageHelper.startPage(page, pageSize);
        return queryFacilitiesByExample(facilityExample);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedResult queryFacilitiesByExample(Example facilityExample) {
        // 通过条件，返回pagedResult
        List<FacilityInfo> list = (List<FacilityInfo>) facilityMapper.selectByPrimaryKey(facilityExample);
        PageInfo<FacilityInfo> pageInfo = new PageInfo<>(list);
        PageInfo<FacilityVO> pageInfoVo= new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, pageInfoVo, "list");

        List<FacilityVO> listVO = new ArrayList<>();
        for (FacilityInfo a : list) {
            listVO.add(composeFacilityVO(a));
        }
        pageInfoVo.setList(listVO);

        // 为最终返回对象 pagedResult 添加属性
        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(pageInfoVo.getPageNum());
        pagedResult.setTotal(pageInfoVo.getPages());
        pagedResult.setRows(pageInfoVo.getList());
        pagedResult.setRecords(pageInfoVo.getTotal());

        return pagedResult;
    }

}
