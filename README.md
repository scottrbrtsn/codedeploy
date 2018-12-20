#codedeploy

Run this on the server you wish to deploy from.

Configure a HTTP hook on your Git, Bitbucket, etc. code respository to sent a request to this service 
every time code is pushed to your development/production/master branch.

Place this .jar in the root of the folder you will pull from.
The folder can be:
    - a folder with another .jar
    - a folder with a mvn project in it
    - a folder with a docker-compose and /images in it
    
    
Needs to be tested
https://ip/deploy/{os}/(branchName}/{deploymentType}
   - os (the os this is running on) = {windows, linux, mac}
   - branchName (the branch to pull from ) a valid, currently checked-out, branchName
   - deploymentType = {docker, mvn, jar}
       - docker requires a valid docker registry

@Requires SSL needs to be set up for this to work (http might work but I doubt you'd want to)

@Requires a public facing ip.