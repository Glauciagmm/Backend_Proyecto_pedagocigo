package com.uniquecare.pedagogico_backend.services;

import com.uniquecare.pedagogico_backend.models.Facility;
import com.uniquecare.pedagogico_backend.repositories.FacilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class FacilityServiceImpl implements IFacilityService {

    private final FacilityRepository facilityRepository;

    @Autowired
    public FacilityServiceImpl(FacilityRepository facilityRepository){
        this.facilityRepository = facilityRepository;
    }

    @Override
    /** works*/
    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    @Override
    public Facility findFacilityById(Long id) {
        return facilityRepository.findById(id).orElse(null);
    }

    /*@Override
    public List<Facility> getAllFacilitiesByCategoryId(Long categoryId) {
        return facilityRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Facility> getAllFacilitiesByCategoryName(String categoryName) {
        return facilityRepository.findAllByCategoryName(categoryName);
    }*/

    /** works*/
    @Override
    public void deleteFacilityById(Long id) {
        facilityRepository.deleteById(id);
    }

    /** works*/
    @Override
    public Facility updateFacility(Facility facility) {
        return facilityRepository.save(facility);
    }

    @Override
    public List<Facility> getContractByUserID(Long userId) {
        return null;
    }

    /** works*/
    @Override
    public Facility addNewFacility(Facility facility){
        return facilityRepository.save(facility);
    }
}
