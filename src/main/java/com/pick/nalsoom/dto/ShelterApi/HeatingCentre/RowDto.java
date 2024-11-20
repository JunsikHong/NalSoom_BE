package com.pick.nalsoom.dto.ShelterApi.HeatingCentre;

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

    @JsonProperty("R_AREA_NM")
    private String rAreaNm;

    @JsonProperty("USE_YN")
    private String useYn;

    @JsonProperty("COLCNT_PUBLIC_USE_YN")
    private String colcntPublicUseYn;

    @JsonProperty("ROAD_NM_ADDR")
    private String roadNmAddr;

    @JsonProperty("LOTNO_ADDR")
    private String lotnoAddr;

    @JsonProperty("EQUP_TYPE")
    private String equpType;

    @JsonProperty("R_AREA_SQR")
    private String rAreaSqr;

    @JsonProperty("USE_PRNB")
    private String usePrnb;

    @JsonProperty("OPER_BGNG_YMD")
    private String operBgngYmd;

    @JsonProperty("OPER_END_YMD")
    private String operEndYmd;

    @JsonProperty("WKDY_OPN_YN")
    private String wkdyOpnYn;

    @JsonProperty("WKDY_END_HR")
    private String wkdyEndHr;

    @JsonProperty("SATDAY_OPN_YN")
    private String satdayOpnYn;

    @JsonProperty("SATDAY_BGNG_HR")
    private String satdayBgngHr;

    @JsonProperty("SATDAY_END_HR")
    private String satdayEndHr;

    @JsonProperty("SUNDAY_OPN_YN")
    private String sundayOpnYn;

    @JsonProperty("SUNDAY_BGNG_HR")
    private String sundayBgngHr;

    @JsonProperty("SUNDAY_END_HR")
    private String sundayEndHr;

    @JsonProperty("HOLDAY_OPN_YN")
    private String holdayOpnYn;

    @JsonProperty("HOLDAY_BGNG_HR")
    private String holDayBgngHr;

    @JsonProperty("HOLDAY_END_HR")
    private String holdayEndHr;

    @JsonProperty("HEAT1_CNT")
    private String heat1Cnt;

    @JsonProperty("HEAT2_CNT")
    private String heat2Cnt;

    @JsonProperty("HEAT3_CNT")
    private String heat3Cnt;

    @JsonProperty("HEAT4_CNT")
    private String heat4Cnt;

    @JsonProperty("CHK1_YN")
    private String chk1Yn;

    @JsonProperty("CHK2_YN")
    private String chk2Yn;

    @JsonProperty("CHK3_YN")
    private String chk3Yn;

    @JsonProperty("CHK4_YN")
    private String chk4Yn;

    @JsonProperty("CHK5_YN")
    private String chk5Yn;

    @JsonProperty("CHK6_YN")
    private String chk6Yn;

    @JsonProperty("CHK7_YN")
    private String chk7Yn;

    @JsonProperty("MNG_DEPT")
    private String mngDept;

    @JsonProperty("MNG_TEL")
    private String mngTel;

    @JsonProperty("D_MNMT_NM")
    private String dMnmtNm;

    @JsonProperty("D_MNMT_TEL")
    private String dMnmtTel;

    @JsonProperty("S_MNMT_NM")
    private String sMnmtNm;

    @JsonProperty("S_MNMT_TEL")
    private String sMnmtTel;

    @JsonProperty("LAT")
    private String lat;

    @JsonProperty("LOT")
    private String lot;

    @JsonProperty("RMRK")
    private String rmrk;

    @JsonProperty("R_SEQ_NO")
    private String rSeqNo;

    @JsonProperty("YEAR")
    private String year;

    @JsonProperty("AREA_CD")
    private String areaCd;

    @JsonProperty("MAP_COORD_X")
    private String mapCoordX;

    @JsonProperty("MAP_COORD_Y")
    private String mapCoordY;

    @JsonProperty("CHK8_YN")
    private String chk8Yn;
}
