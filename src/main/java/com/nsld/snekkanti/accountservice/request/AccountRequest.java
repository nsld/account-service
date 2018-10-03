package com.nsld.snekkanti.accountservice.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequest {
    private String firstName;
    private String secondName;
    private Integer accountNumber;
}
