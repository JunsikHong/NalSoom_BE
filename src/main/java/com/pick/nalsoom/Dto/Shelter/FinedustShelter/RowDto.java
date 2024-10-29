package com.pick.nalsoom.Dto.Shelter.FinedustShelter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RowDto {

    @JsonProperty("SN")
    private String sn;

    @JsonProperty("HJD_NAM")
    private String hjdNam;

    @JsonProperty("SHUNT_NAM")
    private String shuntNam;

    @JsonProperty("ADR_NAM")
    private String adrNam;

    @JsonProperty("EQUP_TYPE")
    private String equpType;

    @JsonProperty("HOU_CNT_M")
    private String houCntM;

    @JsonProperty("TEL_NO_CN")
    private String telNoCn;

    @JsonProperty("WKDY_USE_HR")
    private String wkdyUseHr;

    @JsonProperty("WKND_USE_HR")
    private String wkndUseHr;

    @JsonProperty("MEMBERSHIP_YN")
    private String membershipYn;

    @JsonProperty("MAP_COORD_X")
    private String mapCoordX;

    @JsonProperty("MAP_COORD_Y")
    private String mapCoordY;

    @JsonProperty("AREA")
    private String area;

    @JsonProperty("REMARK")
    private String remark;

}
