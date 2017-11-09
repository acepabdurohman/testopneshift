package com.acepabdurohman.testopenshift;

import lombok.Data;

@Data
public class HeaderSSO {
    private String usrId;
    private long timestamp;
    private String signature;
}
