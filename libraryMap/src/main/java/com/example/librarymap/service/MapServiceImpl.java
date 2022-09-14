package com.example.librarymap.service;

import com.example.librarymap.mapper.LibraryMapMapper;
import com.example.librarymap.pojo.LibraryMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MapServiceImpl implements MapService{

    @Autowired
    private LibraryMapMapper libraryMapMapper;

    @Override
    public LibraryMap getLibraryMapByFloor(int floorNum){
        LibraryMap libraryMap = new LibraryMap();
        libraryMap.setFloorNum(floorNum);
        return libraryMapMapper.selectOne(libraryMap);
    }

    @Override
    public List<LibraryMap> getAllLibraryMap() {
        return libraryMapMapper.selectAll();
    }
}
