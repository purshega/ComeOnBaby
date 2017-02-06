
package com.ComeOnBaby.service;



import com.ComeOnBaby.model.CustomerInfo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("customerInfoService")
public interface CustomerInfoService {

    void addNewCustomerInfo(CustomerInfo customerInfo);
    void updateCustomerInfo(CustomerInfo customerInfo);
    void deleteCustomerInfo(CustomerInfo customerInfo);
    List<CustomerInfo> getAllCustomerInfos();
    CustomerInfo getCustomerInfoById(Long id);


}
