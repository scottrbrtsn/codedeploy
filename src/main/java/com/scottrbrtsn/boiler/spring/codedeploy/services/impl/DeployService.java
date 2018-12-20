package com.scottrbrtsn.boiler.spring.codedeploy.services.impl;

import com.scottrbrtsn.boiler.spring.codedeploy.services.IDeployService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeployService implements IDeployService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeployService.class);

    private static final String WINDOWS = "windows";
    private static final String LINUX = "linux";
    private static final String MAC = "mac";

    private static final String DOCKER = "docker";
    private static final String MVN = "mvn";
    private static final String JAR = "jar";

    private static final String SUCCESS = "SUCCESS";

    /**
     *
     * @param branchName the branchName to pull from
     * @param os the Operating System this service is running on
     * @return SUCCESS or ERROR mesage
     *
     * @requires the .jar to be at the root of a valid git repository
     * @requires to run from a publically accessible ip
     * @requires the desired branch to be checked out
     */
    @Override
    public String deploy (String os, String branchName, String deploymentType){
        String toReturn = openTerminalAndPull(os, branchName);
        if(SUCCESS.equals(toReturn)) {
            toReturn = deploy(os, deploymentType);
        }

        return toReturn;
    }

    private String openTerminalAndPull(String os, String branchName){
        String toReturn = SUCCESS;
        if(WINDOWS.equalsIgnoreCase(os)){
            try {
                //open a window so any error in pulling might show up
                String[] args = new String[] {"cmd", "-c", "git pull origin " + branchName};
                Process proc = new ProcessBuilder(args).start();
                proc.waitFor();//wait until this finishes
            }
            catch(Exception e){
                LOGGER.error(e.getMessage());
                toReturn = "ERROR";
            }
        } else if (LINUX.equalsIgnoreCase(os) || MAC.equalsIgnoreCase(os)){
            try {
                String[] args = new String[] {"/bin/bash", "-c", "git pull origin " + branchName};
                Process proc = new ProcessBuilder(args).start();
                proc.waitFor();//wait until this finishes
            }
            catch(Exception e){
                LOGGER.error(e.getMessage());
                toReturn = "ERROR";
            }
        } else {
            toReturn = "UNKNOWN OS";
        }

        return toReturn;
    }

    private String deploy (String os, String deploymentType){
        String toReturn = SUCCESS;
        if(DOCKER.equalsIgnoreCase(deploymentType)) {
            deployCommand(os, "docker-compose stop");
            deployCommand(os, "docker-compose rm -f");
            deployCommand(os, "docker-compose pull");
            deployCommand(os, "docker-compose up -d");
        } else if(MVN.equalsIgnoreCase(deploymentType)){
           deployCommand(os, "mvn clean spring-boot:run");
        } else if(JAR.equalsIgnoreCase(deploymentType)){
            deployCommand(os, "java -jar *.jar");
        }

        return toReturn;
    }

    private String deployCommand (String os, String command){
        String toReturn = "SUCCESS";
        if(WINDOWS.equalsIgnoreCase(os)){
            try {
                //open a window so any error in pulling might show up
                String[] args = new String[] {"cmd", "-c", command};
                Process proc = new ProcessBuilder(args).start();
                proc.waitFor();//wait until this finishes
            }
            catch(Exception e){
                LOGGER.error(e.getMessage());
                toReturn = "ERROR";
            }
        } else if (LINUX.equalsIgnoreCase(os) || MAC.equalsIgnoreCase(os)){
            try {
                String[] args = new String[] {"/bin/bash", "-c", command};
                Process proc = new ProcessBuilder(args).start();
                proc.waitFor();//wait until this finishes
            }
            catch(Exception e){
                LOGGER.error(e.getMessage());
                toReturn = "ERROR";
            }
        } else {
            toReturn = "UNKNOWN OS";
        }
        return toReturn;
    }

}
