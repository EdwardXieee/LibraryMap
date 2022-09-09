package com.example.librarymap.mapper;

import com.example.librarymap.pojo.ShelfInfo;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper
public interface ShelfInfoMapper extends JpaRepository<ShelfInfo, String> {

}
