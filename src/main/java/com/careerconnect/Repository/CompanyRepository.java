package com.careerconnect.Repository;



import com.careerconnect.Entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository {

    Company save(Company company);

    Optional<Company> findById(String companyId);

    Optional<Company> findByName(String companyName);

    List<Company> findAll();

    void deleteById(String companyId);
}