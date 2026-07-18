package com.careerconnect.Entity;



import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EligibilityResult {

    private String studentId;

    private String driveId;

    private boolean eligible;

    @Builder.Default
    private List<String> reasons = new ArrayList<>();
}
