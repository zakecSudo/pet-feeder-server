package com.degree.petFeeder.dto;

import com.degree.petFeeder.model.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {

    private Long id;
    private boolean active;
    private LocalTime time;
    private List<Schedule.Day> repeatDays;
    private FeedingDTO feeding;
}
