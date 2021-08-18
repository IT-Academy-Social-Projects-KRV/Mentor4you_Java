package com.mentor4you.controller;

import com.mentor4you.model.GroupServices;
import com.mentor4you.model.PlaceOfMentorshipId;
import com.mentor4you.model.Role;
import com.mentor4you.repository.GroupServicesRepository;
import com.mentor4you.repository.PlaceOfMentorRepository;
import com.mentor4you.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/add")
public class SystemController {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PlaceOfMentorRepository placeOfMRep;
    @Autowired
    private GroupServicesRepository groupServicesRepository;

    public SystemController(RoleRepository roleRepository,
                            PlaceOfMentorRepository placeOfMRep,
                            GroupServicesRepository groupServicesRepository) {
        this.roleRepository = roleRepository;
        this.placeOfMRep = placeOfMRep;
        this.groupServicesRepository = groupServicesRepository;
    }

    @GetMapping("/addSystems")
    public String registerRoles(){
      roleRepository.save(new Role("notAutorise"));
      roleRepository.save(new Role("admin"));
      roleRepository.save(new Role("moderator"));
      roleRepository.save(new Role("mentor"));
      roleRepository.save(new Role("mentee"));

      placeOfMRep.save(new PlaceOfMentorshipId("OFFline"));
      placeOfMRep.save(new PlaceOfMentorshipId("ONline"));
      placeOfMRep.save(new PlaceOfMentorshipId("Mix"));

      groupServicesRepository.save(new GroupServices("No"));
      groupServicesRepository.save(new GroupServices("Yes"));
      groupServicesRepository.save(new GroupServices("Mix"));

      return "systems tables added";
    }
}
