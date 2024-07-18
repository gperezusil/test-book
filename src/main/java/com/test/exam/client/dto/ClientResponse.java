package com.test.exam.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {
    private String houseId;
    private String discountCode;
    private Integer id;
    private String userId;
    private Boolean status;
}
