package com.example.enotes_api_service.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditAwareConfig  implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {
        //return Optional.of(1);
        //for testing purpose only
        return Optional.of(2);


    }
}
