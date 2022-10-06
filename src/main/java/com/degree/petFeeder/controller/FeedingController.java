package com.degree.petFeeder.controller;

import com.degree.petFeeder.dto.FeedingDTO;
import com.degree.petFeeder.service.FeedingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/feedings")
public class FeedingController {

    @Autowired
    FeedingService feedingService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<FeedingDTO> getOne(@PathVariable(name = "id") Long id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<>(feedingService.getOne(id), responseHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<FeedingDTO>> getAll(@SortDefault(sort = {"name"}, direction = Sort.Direction.ASC) Sort sort) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<>(feedingService.getAll(sort), responseHeaders, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<FeedingDTO> create(@Valid @RequestBody FeedingDTO dto) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<>(feedingService.create(dto), responseHeaders, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FeedingDTO> update(@PathVariable(name = "id") Long id,
                                             @Valid @RequestBody FeedingDTO dto) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<>(feedingService.update(id, dto), responseHeaders, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        feedingService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/start")
    public ResponseEntity<Void> start(@PathVariable(name = "id") Long id) {
        feedingService.startFeeding(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
