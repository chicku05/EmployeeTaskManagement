package com.employee.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO implements Serializable {

    @NotNull
    @Schema(description = "employee name",example = "xyz")
    private String name;
    @NotNull
    @Email
    @Schema(description = "enter valid email",example = "xyz@xyx.com")
    private String email;
    @NotNull
    @Schema(example = "IT")
    private String department;
    @NotNull
    @ArraySchema(schema = @Schema(description = "define task"))
    private List<TaskDTO> taskDtoList;
}
