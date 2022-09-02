package com.example.librarymap.mapper;

import com.example.librarymap.pojo.FacilityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityMapper extends JpaRepository<FacilityInfo, String> {
}
