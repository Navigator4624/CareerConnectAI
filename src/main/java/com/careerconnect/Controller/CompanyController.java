package com.careerconnect.Controller;



import com.careerconnect.Dto.CompanyRequest;
import com.careerconnect.Dto.CompanyResponse;
import com.careerconnect.Service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // Create Company
    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(
            @Valid @RequestBody CompanyRequest request) {

        CompanyResponse response = companyService.createCompany(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get Company By ID
    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResponse> getCompanyById(
            @PathVariable String companyId) {

        CompanyResponse response = companyService.getCompanyById(companyId);

        return ResponseEntity.ok(response);
    }

    // Get All Companies
    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {

        List<CompanyResponse> companies = companyService.getAllCompanies();

        return ResponseEntity.ok(companies);
    }

    // Update Company
    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable String companyId,
            @Valid @RequestBody CompanyRequest request) {

        CompanyResponse response =
                companyService.updateCompany(companyId, request);

        return ResponseEntity.ok(response);
    }

    // Delete Company
    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteCompany(
            @PathVariable String companyId) {

        String message = companyService.deleteCompany(companyId);

        return ResponseEntity.ok(message);
    }
}
