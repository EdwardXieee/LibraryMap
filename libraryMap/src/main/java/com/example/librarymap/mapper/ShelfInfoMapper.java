package com.example.librarymap.mapper;

import com.example.librarymap.pojo.ShelfInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelfInfoMapper extends JpaRepository<ShelfInfo, String> {
}
