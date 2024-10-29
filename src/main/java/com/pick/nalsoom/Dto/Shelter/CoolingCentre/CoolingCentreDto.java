package com.pick.nalsoom.Dto.Shelter.CoolingCentre;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoolingCentreDto {

    @JsonProperty("TbGtnHwcwP")
    private TbGtnHwcwPDto tbGtnHwcwP;

}
