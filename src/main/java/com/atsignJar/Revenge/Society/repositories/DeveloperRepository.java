package com.atsignJar.Revenge.Society.repositories;

import com.atsignJar.Revenge.Society.models.developer.Developer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface DeveloperRepository extends JpaRepository<Developer,Long> {

    List<Developer> findAllByCohort(Integer cohort, Sort sort);


}
