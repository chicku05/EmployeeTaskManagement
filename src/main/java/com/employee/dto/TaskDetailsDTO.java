package com.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDetailsDTO implements Serializable {

    @NotNull
    @Schema(description = "fill title value",example = "IT")
    private String title;
    @NotNull
    @Schema(description = "fill description value",example = "IT")
    private String description;
    @NotNull
    @Schema(description = "fill status value",example = "PENDING")
    private String status;
    @NotNull
    @Schema(description = "enter valid employee id",example = "1")
    private Long employeeId;
}
