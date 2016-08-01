package com.fingerprint.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fingerprint.domain.EnrollmentStep;

@Repository
public interface EnrollStatusRepository extends CrudRepository<EnrollmentStep, Integer> {
	

}
