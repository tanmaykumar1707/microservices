package com.micro.accounts.dto;


import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConfigurationProperties("accounts")  commented due to record is final and  value cant be change if props get change 
//creating class for props
public record AccountsContactInfoDto2_NotInUse(String message,Map<String,String> contactDetails, List<String> onCallSupport) {

}
