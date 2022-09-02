package com.example.librarymap.mapper;

import com.example.librarymap.pojo.LibraryMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryMapMapper extends JpaRepository<LibraryMap, String> {
}
