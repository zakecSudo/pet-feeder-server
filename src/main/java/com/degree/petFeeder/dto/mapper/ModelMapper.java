package com.degree.petFeeder.dto.mapper;

import com.degree.petFeeder.dto.FeedingDTO;
import com.degree.petFeeder.dto.ScheduleDTO;
import com.degree.petFeeder.dto.ScheduleStorableDTO;
import com.degree.petFeeder.error.ApiException;
import com.degree.petFeeder.model.Feeding;
import com.degree.petFeeder.model.Schedule;
import com.degree.petFeeder.repository.FeedingRepository;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    FeedingDTO modelToDto(Feeding model);

    List<FeedingDTO> modelToFeedingsDto(List<Feeding> models);

    @Mapping(target = "id", ignore = true)
    Feeding dtoToModel(FeedingDTO dto);

    @InheritConfiguration(name = "dtoToModel")
    Feeding updateFeeding(FeedingDTO dto, @MappingTarget Feeding model);

    ScheduleDTO modelToDto(Schedule model);

    List<ScheduleDTO> modelToSchedulesDto(List<Schedule> models);

    @Mapping(target = "feeding", source = "feedingId", qualifiedByName = "idToFeeding")
    Schedule dtoToModel(ScheduleStorableDTO dto, @Context FeedingRepository feedingRepository);

    @InheritConfiguration(name = "dtoToModel")
    Schedule updateSchedule(ScheduleStorableDTO dto, @MappingTarget Schedule model, @Context FeedingRepository feedingRepository);

    @Named("idToFeeding")
    default Feeding idToFeeding(Long id, @Context FeedingRepository feedingRepository) {
        return feedingRepository.findById(id).orElseThrow(() -> new ApiException(ApiException.RESOURCE_NOT_FOUND, String.format("Hranjenje %s ne obstaja.", id)));
    }
}
