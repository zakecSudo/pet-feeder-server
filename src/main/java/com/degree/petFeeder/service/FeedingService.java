package com.degree.petFeeder.service;

import com.degree.petFeeder.dto.FeedingDTO;
import com.degree.petFeeder.dto.mapper.ModelMapper;
import com.degree.petFeeder.error.ApiException;
import com.degree.petFeeder.model.Feeding;
import com.degree.petFeeder.repository.FeedingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedingService {

    @Autowired
    FeedingRepository feedingRepository;

    @Autowired
    MqttService mqttService;

    @Autowired
    ModelMapper modelMapper;

    public FeedingDTO getOne(Long id) {
        return modelMapper.modelToDto(feedingRepository.findById(id).orElseThrow(() -> new ApiException(ApiException.RESOURCE_NOT_FOUND, String.format("Hranjenje %s ne obstaja.", id))));
    }

    public List<FeedingDTO> getAll(Sort sort) {
        return modelMapper.modelToFeedingsDto(feedingRepository.findAll(sort));
    }

    public FeedingDTO create(FeedingDTO dto) {
        if (dto.getDurationSeconds() < 0.008) {
            throw new ApiException(ApiException.INVALID_INPUT_FORMAT, "Vrtenje motorja mora biti večje ali enako 0.008s.");
        }
        Feeding model = modelMapper.dtoToModel(dto);
        return modelMapper.modelToDto(feedingRepository.save(model));
    }

    public FeedingDTO update(Long id, FeedingDTO dto) {
        if (dto.getDurationSeconds() < 0.008) {
            throw new ApiException(ApiException.INVALID_INPUT_FORMAT, "Vrtenje motorja mora biti večje ali enako 0.008s.");
        }
        Feeding model = feedingRepository.findById(id).orElseThrow(() -> new ApiException(ApiException.RESOURCE_NOT_FOUND, String.format("Hranjenje %s ne obstaja.", id)));
        modelMapper.updateFeeding(dto, model);

        return modelMapper.modelToDto(feedingRepository.save(model));
    }

    public void delete(Long id) {
        feedingRepository.deleteById(id);
    }

    public void startFeeding(Long id) {
        Feeding model = feedingRepository.findById(id).orElseThrow(() -> new ApiException(ApiException.RESOURCE_NOT_FOUND, String.format("Hranjenje %s ne obstaja.", id)));
        mqttService.turnMotor(model.getDurationSeconds());
    }
}
