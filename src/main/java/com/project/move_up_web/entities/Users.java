package com.project.move_up_web.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.move_up_web.entities.shared.PrimaryId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users extends PrimaryId {
    @Column(name = "email")
    private String email;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private String status;

    @JsonIgnoreProperties(value = {"createAt", "updateAt", "createBy", "updateBy", "deleted"})
    @ManyToOne
    @JoinColumn(name = "roleId")
    private Roles role;
}
