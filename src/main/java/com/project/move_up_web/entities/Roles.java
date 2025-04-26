package com.project.move_up_web.entities;

import com.project.move_up_web.entities.shared.PrimaryId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Roles extends PrimaryId {
    @Column(name = "roleName")
    private String roleName;
    @Column(name = "status")
    private String status;
}
