package com.degree.petFeeder.utils;

import com.degree.petFeeder.model.Schedule;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScheduleUtils {

    public static List<Schedule> sortUpcoming(List<Schedule> schedules) {
        int currentDayIndex = Arrays.stream(Schedule.Day.values()).map(day -> day.name()).collect(Collectors.toList()).indexOf(LocalDateTime.now().getDayOfWeek().name());
        List<Schedule.Day> initialSequence = Arrays.asList(Schedule.Day.values());
        List<Schedule.Day> days = Stream.concat(initialSequence.subList(currentDayIndex, initialSequence.size()).stream(),
                        initialSequence.subList(0, currentDayIndex).stream())
                .collect(Collectors.toList());

        Collections.singletonList(days.get(0));

        List<Schedule> first = schedules.stream().filter(schedule -> schedule.getRepeatDays().contains(days.get(0)) && schedule.getTime().isAfter(LocalTime.now()))
                .collect(Collectors.toList());
        first.forEach(schedule -> schedule.setRepeatDays(Arrays.asList(days.get(0))));

        List<Schedule> last = schedules.stream().filter(schedule -> schedule.getRepeatDays().contains(days.get(0)) && schedule.getTime().isBefore(LocalTime.now()))
                .collect(Collectors.toList());
        last.forEach(schedule -> schedule.setRepeatDays(Arrays.asList(days.get(0))));


        schedules.removeAll(first);
        schedules.removeAll(last);
        List<Schedule> sortedSchedules = new ArrayList<>(first);
        for (Schedule.Day day : days.subList(1, days.size())) {
            for (Schedule schedule : schedules) {
                if (schedule.getRepeatDays().contains(day) && !sortedSchedules.contains(schedule)) {
                    sortedSchedules.add(new Schedule(schedule.getId(), schedule.isActive(), schedule.getTime(), Arrays.asList(day), schedule.getFeeding()));
                }
            }
        }
        sortedSchedules.addAll(last);

        return sortedSchedules;
    }

}
