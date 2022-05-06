package com.betvictor.chatapp.endpoint.actionmonitor;

import com.betvictor.chatapp.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Slf4j
@Component
public class UserActionMonitor extends ActionMonitor {

    @PostUpdate
    private void afterUpdate(User user) {
        afterChange(user.getId(), "updated");
    }

    @PostPersist
    private void afterPersist(User user) {
        afterChange(user.getId(), "inserted");
    }

}
