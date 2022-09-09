package com.example.librarymap.mapper;

import com.example.librarymap.pojo.LibraryMap;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface LibraryMapMapper extends JpaRepository<LibraryMap, String> {
    @Query(value = "select * from library.library_map order by floor_num",nativeQuery=true)
    List<LibraryMap> queryMap();

    @Query(value = "select * from library.library_map lm where lm.floor_num=:floorNum",nativeQuery=true)
    LibraryMap getMapByFloor(@Param("floorNum") Integer floorNum);
}
