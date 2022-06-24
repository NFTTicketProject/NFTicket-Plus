package ssafy.nfticket.dto.request.show;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleShowCategoryDto {
    @NotBlank
    @JsonProperty(value = "category_name")
    private String categoryName;

    public SimpleShowCategoryDto(String categoryName) {
        this.categoryName = categoryName;
    }
}
