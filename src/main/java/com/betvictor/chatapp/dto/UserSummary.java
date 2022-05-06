package com.betvictor.chatapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSummary {

    private int id;
    private String username;
    private String name;
    private String profilePicture;
}
