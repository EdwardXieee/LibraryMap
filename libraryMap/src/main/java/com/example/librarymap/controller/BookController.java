package com.example.librarymap.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "书籍相关接口", tags = { "Book-Controller" })
@RequestMapping("/book")
public class BookController {
}
