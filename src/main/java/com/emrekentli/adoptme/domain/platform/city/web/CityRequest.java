package com.emrekentli.adoptme.domain.platform.city.web;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CityRequest {

    @NotNull(message = "validation.required.status")
    private Boolean status;

    @NotNull(message = "validation.required.name")
    @NotBlank(message = "validation.required.name")
    @NotEmpty(message = "validation.required.name")
    private String name;

}
