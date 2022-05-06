package com.betvictor.chatapp.model;

import com.betvictor.chatapp.endpoint.actionmonitor.UserActionMonitor;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(UserActionMonitor.class)
@Entity
@Table(name="user", uniqueConstraints=@UniqueConstraint(columnNames={"email"}))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message="valid username required")
    private String username;

    @NotNull(message="valid password required")
    private String password;

    @NotNull(message="valid email required")
    @Email(message = "valid email required")
    private String email;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    private boolean active;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String profilePictureUrl;

    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.email = user.email;
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.active = user.active;
        this.role = user.role;
        this.profilePictureUrl = user.profilePictureUrl;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = true;
        this.role = Role.USER;
    }

}
