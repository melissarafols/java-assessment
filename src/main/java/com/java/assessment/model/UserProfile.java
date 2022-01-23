package com.java.assessment.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="TBL_USERS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = UserProfile.class)
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_users_seq")
    @SequenceGenerator(name = "tbl_users_seq", allocationSize = 1)
    @Column(name="id")
    private Long id;

    @JsonProperty("username")
    @Column(name="username")
    private String username;

    @JsonProperty("first_name")
    @Column(name="first_name")
    private String firstName;

    @JsonProperty("last_name")
    @Column(name="last_name")
    private String lastName;
}
