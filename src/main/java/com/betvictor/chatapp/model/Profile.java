package com.betvictor.chatapp.model;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    private String displayName;
    private String profilePictureUrl;
}
