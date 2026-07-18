package com.careerconnect.Repository;



import com.careerconnect.Entity.Company;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryCompanyRepository implements CompanyRepository {

    private final Map<String, Company> companies = new HashMap<>();

    @Override
    public Company save(Company company) {
        companies.put(company.getCompanyId(), company);
        return company;
    }

    @Override
    public Optional<Company> findById(String companyId) {
        return Optional.ofNullable(companies.get(companyId));
    }

    @Override
    public Optional<Company> findByName(String companyName) {
        return companies.values()
                .stream()
                .filter(company -> company.getCompanyName().equalsIgnoreCase(companyName))
                .findFirst();
    }

    @Override
    public List<Company> findAll() {
        return new ArrayList<>(companies.values());
    }

    @Override
    public void deleteById(String companyId) {
        companies.remove(companyId);
    }
}