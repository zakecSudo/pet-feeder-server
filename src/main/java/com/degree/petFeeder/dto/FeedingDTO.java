package com.degree.petFeeder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedingDTO {

    private Long id;
    private String name;
    private String description;
    private Float durationSeconds;

}
