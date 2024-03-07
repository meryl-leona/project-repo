package com.app.messenger.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_account")
public class UserAccount {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String nickname;
    private String firstname;
    private String lastname;

}
