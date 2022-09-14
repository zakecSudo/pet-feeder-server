package com.degree.petFeeder.service;

import com.degree.petFeeder.dto.ScheduleDTO;
import com.degree.petFeeder.dto.ScheduleStorableDTO;
import com.degree.petFeeder.dto.mapper.ModelMapper;
import com.degree.petFeeder.error.ApiException;
import com.degree.petFeeder.model.Schedule;
import com.degree.petFeeder.repository.FeedingRepository;
import com.degree.petFeeder.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    FeedingRepository feedingRepository;

    @Autowired
    ModelMapper modelMapper;

    public ScheduleDTO getOne(Long id) {
        return modelMapper.modelToDto(scheduleRepository.findById(id).orElseThrow(() -> new ApiException(ApiException.RESOURCE_NOT_FOUND, String.format("Urnik %s ne obstaja.", id))));
    }

    public List<ScheduleDTO> getAll(Sort sort) {
        return modelMapper.modelToSchedulesDto(scheduleRepository.findAll(sort));
    }

    public ScheduleDTO create(ScheduleStorableDTO dto) {
        Schedule model = modelMapper.dtoToModel(dto, feedingRepository);
        return modelMapper.modelToDto(scheduleRepository.save(model));
    }

    public ScheduleDTO update(Long id, ScheduleStorableDTO dto) {
        Schedule model = scheduleRepository.findById(id).orElseThrow(() -> new ApiException(ApiException.RESOURCE_NOT_FOUND, String.format("Urnik %s ne obstaja.", id)));
        modelMapper.updateSchedule(dto, model, feedingRepository);

        return modelMapper.modelToDto(scheduleRepository.save(model));
    }

    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }
}
