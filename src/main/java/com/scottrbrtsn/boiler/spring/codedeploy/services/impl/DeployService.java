package com.scottrbrtsn.boiler.spring.codedeploy.services.impl;

import com.scottrbrtsn.boiler.spring.codedeploy.services.IDeployService;
import org.springframework.stereotype.Service;

@Service
public class DeployService implements IDeployService {

    /**
     *
     * @param os the Operating System this service is running on
     * @param repositoryName the name of the repository to be pulled from
     * @return SUCCESS or ERROR mesage
     */
    @Override
    public String deploy (String os, String repositoryName){




        return "SUCCESS";
    }
}
