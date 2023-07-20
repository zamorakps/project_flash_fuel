package com.flashfuel.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.flashfuel.project.model.ClientInformation;

@Repository
public interface ClientInformationRepository extends JpaRepository<ClientInformation, Long> {}
