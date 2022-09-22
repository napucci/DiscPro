package com.capstone.discgolfstore.entities;

import com.capstone.discgolfstore.dtos.UserDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<CartDisc> cartDiscSet = new HashSet<>();

    public User(UserDto userDto){
        if(userDto.getUsername() != null){
            this.username = userDto.getUsername();
        }
        if(userDto.getPassword() != null){
            this.password = userDto.getPassword();
        }
        if(userDto.getEmail() != null){
            this.email = userDto.getEmail();
        }
    }

}
