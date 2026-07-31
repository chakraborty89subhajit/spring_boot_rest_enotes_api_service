package com.example.enotes_api_service.config;

import com.example.enotes_api_service.entity.User;
import com.example.enotes_api_service.util.CommonUtil;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditAwareConfig  implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {
        //return Optional.of(1);
        //for testing purpose only
       //return Optional.of(2);
        //getting logged in user id
        User loggedInUser= CommonUtil.getLoggedinUser();
        return Optional.of(loggedInUser.getId());


    }
}
