package com.assignment.domin;

import lombok.Data;
import lombok.ToString;

/**
 * Domain class to hold the NACE information.
 */
@Data
@ToString
public class NaceDetail {
    private Integer orderNumber;
    private Integer level;
    private String code;
    private String parent;
    private String description;
    private String itemInclude;
    private String additionalItemInclude;
    private String rulings;
    private String itemExclude;
    private String referenceToIsic;
}
