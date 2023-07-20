package com.flashfuel.project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.flashfuel.project.model.FuelQuote;
import com.flashfuel.project.model.UserCredentials;

@Repository
public interface FuelQuoteRepository extends JpaRepository<FuelQuote, Long> {
    List<FuelQuote> findByUser(UserCredentials user);
}
