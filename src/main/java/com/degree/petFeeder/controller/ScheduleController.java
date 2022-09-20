package com.degree.petFeeder.controller;

import com.degree.petFeeder.dto.ScheduleDTO;
import com.degree.petFeeder.dto.ScheduleStorableDTO;
import com.degree.petFeeder.service.ScheduleService;
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
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ScheduleDTO> getOne(@PathVariable(name = "id") Long id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<>(scheduleService.getOne(id), responseHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<ScheduleDTO>> getAll(@SortDefault(sort = {"time", "active"}, direction = Sort.Direction.DESC) Sort sort) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<>(scheduleService.getAll(sort), responseHeaders, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ScheduleDTO> create(@Valid @RequestBody ScheduleStorableDTO dto) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<>(scheduleService.create(dto), responseHeaders, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ScheduleDTO> update(@PathVariable(name = "id") Long id,
                                              @Valid @RequestBody ScheduleStorableDTO dto) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<>(scheduleService.update(id, dto), responseHeaders, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
