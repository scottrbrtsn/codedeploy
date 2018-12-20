# codedeploy

Run this on the server you wish to deploy from.
        
Place this .jar in the root of the folder you will pull from.
The folder can be:
   - a folder with another .jar
   - a folder with a mvn project in it
   - a folder with a docker-compose in it  
       -  (this assumes you have a working knowledge of docker and how to set up a project to build images and send to a registry)
    
    
Needs to be tested
https://ip/deploy/{os}/(branchName}/{deploymentType}
   - os (the os this is running on) = {windows, linux, mac}
   - branchName (the branch to pull from ) a valid, currently checked-out, branchName
   - deploymentType = {docker, mvn, jar}
       - docker requires a valid docker registry

@Requires SSL needs to be set up for this to work (http might work but I doubt you'd want to)

@Requires a public facing ip.


## Setup 
Configure a HTTP hook on your Git, Bitbucket, etc. code respository to sent a request to this service 
every time code is pushed to your development/production/master branch.

#### OR
Add this to the pom.xml of the project you are trying to deploy



```
<plugin>
          <groupId>org.codehaus.mojo</groupId>
          <artifactId>exec-maven-plugin</artifactId>
          <version>1.2</version>
          <executions>
            <execution>
              <id>promote-image</id>
              <phase>deploy</phase>
              <goals>
                <goal>exec</goal>
              </goals>
              <configuration>
                <executable>curl</executable>
                <arguments>
                  <argument>https://ip/deploy/linux/development/jar</argument>
                </arguments>
              </configuration>
            </execution>
          </executions>
 </plugin>
 ```