package com.example.librarymap.mapper;

import com.example.librarymap.pojo.FacilityInfo;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Mapper
public interface FacilityMapper extends JpaRepository<FacilityInfo, String> {
    @Query(value = "select * from library.facility_info fi where fi.id=:id", nativeQuery = true)
    FacilityInfo getFacilityById(@Param("id") String id);

}
