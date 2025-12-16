package com.devtiro.blog.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTagsRequest {

    @NotBlank(message="Tag name is required")
    @Size(max=10, message="Maximum {max} tags are allowed")
    private Set<
            @Size(min=2, max=50, message="Category name must be netween {min} and {max} size")
            @Pattern(regexp="^[\\w\\s-]+$", message="Category name can only contain letters,numbers,spaces and hyphens")
                    String> names;

}
