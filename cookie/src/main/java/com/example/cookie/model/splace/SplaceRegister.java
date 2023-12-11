package com.example.cookie.model.splace;

import lombok.Data;

@Data
public class SplaceRegister {

    private String place_name;
    private String place_cat;
    private int place_count;
    private String place_address;
    private String place_content;
    private int place_price;

    public static Splace toSplace(SplaceRegister splaceRegister) {
        Splace splace = new Splace();
        splace.setPlace_name(splaceRegister.getPlace_name());
        splace.setPlace_cat(splaceRegister.getPlace_cat());
        splace.setPlace_count(splaceRegister.getPlace_count());
        splace.setPlace_address(splaceRegister.getPlace_address());
        splace.setPlace_content(splaceRegister.getPlace_content());
        splace.setPlace_price(splaceRegister.getPlace_price());
        return splace;
    }
}
