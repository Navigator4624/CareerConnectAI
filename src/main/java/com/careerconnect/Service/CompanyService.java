package com.careerconnect.Service;



import com.careerconnect.Dto.CompanyRequest;
import com.careerconnect.Dto.CompanyResponse;
import com.careerconnect.Entity.Company;
import com.careerconnect.Repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // Create Company
    public CompanyResponse createCompany(CompanyRequest request) {

        companyRepository.findByName(request.getCompanyName())
                .ifPresent(company -> {
                    throw new RuntimeException("Company already exists.");
                });

        Company company = Company.builder()
                .companyId("COM-" + UUID.randomUUID().toString().substring(0, 8))
                .companyName(request.getCompanyName())
                .sector(request.getSector())
                .description(request.getDescription())
                .build();

        companyRepository.save(company);

        return mapToResponse(company);
    }

    // Get Company By Id
    public CompanyResponse getCompanyById(String companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found."));

        return mapToResponse(company);
    }

    // Get All Companies
    public List<CompanyResponse> getAllCompanies() {

        return companyRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Update Company
    public CompanyResponse updateCompany(String companyId,
                                         CompanyRequest request) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found."));

        // Check duplicate company name
        companyRepository.findByName(request.getCompanyName())
                .ifPresent(existing -> {
                    if (!existing.getCompanyId().equals(companyId)) {
                        throw new RuntimeException("Company name already exists.");
                    }
                });

        company.setCompanyName(request.getCompanyName());
        company.setSector(request.getSector());
        company.setDescription(request.getDescription());

        companyRepository.save(company);

        return mapToResponse(company);
    }

    // Delete Company
    public String deleteCompany(String companyId) {

        companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found."));

        companyRepository.deleteById(companyId);

        return "Company deleted successfully.";
    }

    // Entity -> DTO Mapping
    private CompanyResponse mapToResponse(Company company) {

        return CompanyResponse.builder()
                .companyId(company.getCompanyId())
                .companyName(company.getCompanyName())
                .sector(company.getSector())
                .description(company.getDescription())
                .build();
    }
}
