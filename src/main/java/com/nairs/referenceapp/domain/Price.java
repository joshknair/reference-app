package com.nairs.referenceapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Price {
    BigDecimal amount;
    LocalDateTime effectiveDateTime;

    @JsonIgnore
    Item item;
}
