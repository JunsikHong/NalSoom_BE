package com.pick.nalsoom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplaintDto {

    private Long complaintProperNum;
    private String complaintType;
    private String complaintContent;
    private Long reviewProperNum;
    private Long userProperNum;

}
