package com.scottrbrtsn.boiler.spring.codedeploy.services;

public interface IDeployService {

    String deploy(String os, String branchName, String deploymentType);
}
