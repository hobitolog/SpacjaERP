package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.Role;
import pl.poznan.put.student.spacjalive.erp.service.RoleService;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/list")
    public String listRoles(Model model) {

        List<Role> roles = roleService.getRoles();

        model.addAttribute("roles", roles);

        return "list-roles";
    }

    @GetMapping("/addRoleForm")
    public String addRoleForm(Model model) {

        Role role = new Role();

        model.addAttribute("role", role);

        return "add-role-form";
    }

    @PostMapping("/addRole")
    public String addRole(@ModelAttribute("role") Role role) {

        roleService.saveRole(role);

        return "redirect:/role/list";
    }

    @GetMapping("/deleteRole")
    public String addRoleForm(@RequestParam("roleId") int id) {

        roleService.deleteRole(id);

        return "redirect:/role/list";
    }

    @GetMapping("/updateRoleForm")
    public String updateRoleForm(@RequestParam("roleId") int id, Model model) {

        Role role = roleService.getRole(id);

        model.addAttribute("role", role);

        return "add-role-form";
    }

}
