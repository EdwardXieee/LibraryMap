package com.example.librarymap.service;

import com.example.librarymap.pojo.LibraryMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MapService {

    LibraryMap getLibraryMapByFloor(int floorNum);

    List<LibraryMap> getAllLibraryMap();
}
