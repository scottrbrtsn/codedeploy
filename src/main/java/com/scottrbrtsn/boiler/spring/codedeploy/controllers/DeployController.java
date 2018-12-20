package com.scottrbrtsn.boiler.spring.codedeploy.controllers;

import com.scottrbrtsn.boiler.spring.codedeploy.services.IDeployService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/deploy")
public class DeployController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeployController.class);

    @Autowired
    IDeployService deployService;

    @RequestMapping(value = "/{os}/(branchName}/{deploymentType}", method = RequestMethod.GET)
    public ResponseEntity<String> deploy(@PathVariable String os,
                                         @PathVariable String branchName,
                                         @PathVariable String deploymentType) {
        LOGGER.debug("getATOs");
        return new ResponseEntity<>(deployService.deploy(os, branchName, deploymentType), new HttpHeaders(), HttpStatus.OK);
    }
}