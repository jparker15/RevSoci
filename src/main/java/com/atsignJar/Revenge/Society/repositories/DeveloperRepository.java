package com.atsignJar.Revenge.Society.repositories;

import com.atsignJar.Revenge.Society.models.developer.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer,Long> {
}
