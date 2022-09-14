package com.degree.petFeeder.controller;

import com.degree.petFeeder.dto.FeedingDTO;
import com.degree.petFeeder.dto.ScheduleDTO;
import com.degree.petFeeder.dto.ScheduleStorableDTO;
import com.degree.petFeeder.service.FeedingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/feedings")
public class FeedingController {

    @Autowired
    FeedingService feedingService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<FeedingDTO> getOne(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(feedingService.getOne(id), HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<FeedingDTO>> getAll(@SortDefault(sort = {"name"}, direction = Sort.Direction.DESC) Sort sort) {
        return new ResponseEntity<>(feedingService.getAll(sort), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<FeedingDTO> create(@Valid @RequestBody FeedingDTO dto) {
        return new ResponseEntity<>(feedingService.create(dto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FeedingDTO> update(@PathVariable(name = "id") Long id,
                                             @Valid @RequestBody FeedingDTO dto) {
        return new ResponseEntity<>(feedingService.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        feedingService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
