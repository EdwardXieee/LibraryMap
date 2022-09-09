package com.example.librarymap.mapper;

import com.example.librarymap.pojo.LibraryMap;
import com.example.librarymap.pojo.SearchRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRecordMapper extends JpaRepository<SearchRecord, String> {
    
}
