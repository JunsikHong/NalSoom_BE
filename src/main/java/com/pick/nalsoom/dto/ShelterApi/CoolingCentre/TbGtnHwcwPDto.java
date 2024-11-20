package com.pick.nalsoom.dto.ShelterApi.CoolingCentre;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbGtnHwcwPDto {

    @JsonProperty("list_total_count")
    private int listTotalCount;

    @JsonProperty("RESULT")
    private ResultDto result;

    @JsonProperty("row")
    private List<RowDto> row;
}
