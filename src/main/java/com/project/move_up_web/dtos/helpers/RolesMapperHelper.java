package com.project.move_up_web.dtos.helpers;

import com.project.move_up_web.entities.Role;
import org.mapstruct.Named;

public class RolesMapperHelper {
  @Named("mapRoleFromId")
  public static Role mapRoleFromId(Long roleId) {
    if (roleId == null) return null;
    Role role = new Role();
    role.setId(roleId); // only set the ID, leave other fields null
    return role;
  }
}
