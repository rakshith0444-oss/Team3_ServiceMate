package com.servicemate.servicemate.repository;

import com.servicemate.servicemate.model.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {

    // Get all providers for a given category e.g. "ac-repair"
    List<ServiceProvider> findByCategory(String category);

    // Get only available providers for a category
    List<ServiceProvider> findByCategoryAndAvailable(String category, boolean available);

}