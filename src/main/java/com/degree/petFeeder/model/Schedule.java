package com.degree.petFeeder.model;

import com.degree.petFeeder.enums.KeyEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;
    private boolean active;
    private LocalTime time;
    @ElementCollection(targetClass = Day.class)
    @JoinTable(name = "schedule_repeat_days", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "day", nullable = false)
    @Enumerated(EnumType.STRING)
    List<Day> repeatDays;
    @ManyToOne
    private Feeding feeding;

    @AllArgsConstructor
    public enum Day implements KeyEnum {

        MONDAY("monday"),
        TUESDAY("tuesday"),
        WEDNESDAY("wednesday"),
        THURSDAY("thursday"),
        FRIDAY("friday"),
        SATURDAY("saturday"),
        SUNDAY("sunday");

        private String key;

        @JsonValue
        @Override
        public String getKey() {
            return key;
        }

        @Override
        public void setKey(String key) {
            this.key = key;
        }
    }

}
