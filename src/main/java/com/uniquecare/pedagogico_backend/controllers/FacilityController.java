package com.uniquecare.pedagogico_backend.controllers;

import com.uniquecare.pedagogico_backend.models.*;
import com.uniquecare.pedagogico_backend.repositories.CategoryRepository;
import com.uniquecare.pedagogico_backend.repositories.RoleRepository;
import com.uniquecare.pedagogico_backend.repositories.UserRepository;
import com.uniquecare.pedagogico_backend.security.services.UserDetailsImpl;
import com.uniquecare.pedagogico_backend.services.IFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/facility")
@CrossOrigin(origins="*")
public class FacilityController {
    @Autowired
    private final IFacilityService facilityService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/save")
   /* @PreAuthorize("hasRole('USER')")*/
    public ResponseEntity<Facility> addFacility(@AuthenticationPrincipal UserDetailsImpl user_logged, User assistant,@RequestBody Facility facility) {
        UserDetailsImpl facility_assist = user_logged;
        User userAssistant = assistant;
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_FACILITY"));
        facility.setAssistant(assistant);
        Facility _facility = facilityService.addFacility(new Facility(facility.getTitle(), facility.getDescription(),
        facility.getPricePerHour(),facility.getAssistant(),facility.getCategories(),facility.getAssistant()));
        return new ResponseEntity<>(_facility, HttpStatus.CREATED);
    }
    @GetMapping("/list/{categoryId}")
    public ResponseEntity<List<Facility>> getAllFacilitiesByCategoryId(@PathVariable(value = "categoryId") Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Not found Tag  with id = " + categoryId);
        }
        List<Facility> facilities = facilityService.getAllFacilitiesByCategoriesId(categoryId);
        return new ResponseEntity<>(facilities, HttpStatus.OK);
    }
/*        Optional<Category> OptionalCategory= categoryRepository.findById(facility.getCategories().add());
        Optional<User>OptionalUser= userRepository.findByUsername(user.getUsername());
         if(!OptionalCategory.isPresent()||!OptionalUser.isPresent()){
             return ResponseEntity.unprocessableEntity().build();
         }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        facility.setAssistant(OptionalUser.get());
        facility.setCategories((List<Category>) OptionalCategory.get());

        System.out.println(facility);
         Facility facilitysaved = facilityService.addFacility(facility);
         URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id")
                 .buildAndExpand(facilitysaved.getId()).toUri();
         return ResponseEntity.created(uri).body(facilitysaved);*/


    @GetMapping("/{id}")
    public ResponseEntity<Facility> findFacilityById(@PathVariable Long id){
        return ResponseEntity.ok().body(facilityService.findFacilityById(id));
    }
 /*   @GetMapping("/category/{CategoryId}")
    public ResponseEntity<List<Facility>> findFacilityByCategoryId(@PathVariable("CategoryId") Long CategoryId){

       return ResponseEntity.ok().body(facilityService.getAllFacilitiesByCategoryId(CategoryId));
    }*/

/*    @GetMapping("/{categoryName}")
    public ResponseEntity<List<Facility>> findFacilityByCategoryName(@PathVariable("categoryName") String categoryName) {
    return ResponseEntity.ok().body(facilityService.getAllFacilitiesByCategoryName(categoryName));
    }*/

    @GetMapping("/list1")
    public ResponseEntity<List<Facility>>getFacility(Authentication authentication, HttpSession session){
        if (authentication == null){
            System.out.println("Es necesario que hagas el login");
        }else{
            String username = authentication.getPrincipal().toString();
            System.out.println(username);
        }return ResponseEntity.ok().body(facilityService.getAllFacilities());
    }

    @GetMapping("/list2")
    public ResponseEntity<List<Facility>>getFacilities(){
        return ResponseEntity.ok().body(facilityService.getAllFacilities());
    }

/*    @PostMapping("/create")
    public ResponseEntity<Facility> addFacility(Authentication authentication, @RequestBody Facility facility) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/facility/save").toUriString());
     *//*   if (authentication == null) {
            System.out.println("Es necesario que hagas el login");
        } else { }*//*
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userRepository.findUserById(userDetails.getId());
            System.out.println(user);


        return ResponseEntity.created(uri).body(facilityService.addFacility(facility));
    }*/

    @PutMapping("/edit")
    public ResponseEntity<Facility> editFacility(@RequestBody Facility facility){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/facility/save").toUriString());
        return ResponseEntity.created(uri).body(facilityService.updateFacility(facility));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFacilityById(@PathVariable Long id){
        facilityService.deleteFacilityById(id);
        return ResponseEntity.noContent().build();
    }
}


/* @GetMapping("/list")
    public ResponseEntity<List<Facility>>getFacility(Authentication authentication, Facility facility) {
     if (authentication == null) {
         System.out.println("Es necesario que hagas el login");
     } else {
         String username = authentication.getPrincipal().toString();
         System.out.println(username);
     }
     return ResponseEntity.ok().body(facilityService.getAllFacilities(facility));
    }*/
