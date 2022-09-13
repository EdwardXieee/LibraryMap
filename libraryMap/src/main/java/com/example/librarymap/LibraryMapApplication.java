package com.example.librarymap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
public class LibraryMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryMapApplication.class, args);
    }

}
