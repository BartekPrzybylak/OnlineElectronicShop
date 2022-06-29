package com.example.onlineshop.controller;

import com.example.onlineshop.dto.CategoryDTO;
import com.example.onlineshop.model.Category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private AtomicLong index = new AtomicLong(0);

    private List<Category> categories = new LinkedList<>() {
        {
            add(new Category(nextIdx(), 1L, "AGD"));
            add(new Category(nextIdx(), 1L, "Komputery"));
            add(new Category(nextIdx(), 1l, "Smartfony"));
            add(new Category(nextIdx(), 1l, "TV i Audio"));
        }
    };

    @GetMapping
    public List<Category> getCategories() {
        return categories;
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id) {
        return getCategories().get(id.intValue() - 1);
    }

    @PostMapping
    public ResponseEntity addCategory(@RequestBody CategoryDTO dto) {
        System.out.println(dto);
        Category newCategory = dto.toCategory(nextIdx());
        categories.add(newCategory);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private long nextIdx() {
        return index.incrementAndGet();
    }
}

