package com.degree.petFeeder.repository;

import com.degree.petFeeder.model.Feeding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FeedingRepository extends JpaRepository<Feeding, Long> {
}
