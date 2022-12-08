
def cloneCode() {
   //cloning code 
// The below will clone your repo and will be checked out to master branch by default.
           git credentialsId: 'github', url: 'https://github.com/Leith-mh/SpringBoot-API-CICD.git'
           // Do a ls -lart to view all the files are cloned. It will be clonned. This is just for you to be sure about it.
           sh "ls -lart ./*" 
           // List all branches in your repo. 
           sh "git branch -a"
           // Checkout to a specific branch in your repo.
           sh "git checkout master"
} 

def unitTest() {
    echo 'testing the application...'
} 
def buildJar() {
    echo 'building the application...'
    sh 'mvn -f ./pom.xml clean package'
    sh 'mvn -version'
} 

def buildImage(String imageName) {
    echo 'building the docker image...'
    sh " docker build -t $imageName ."
}


def pushImageNexus(String NEXUS_VERSION,String NEXUS_PROTOCOL,String NEXUS_URL,String NEXUS_REPOSITORY,String NEXUS_CREDENTIAL_ID) {
  pom = readMavenPom file: "pom.xml";
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: "pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
}

def pushImageDocker(String imageName) {
   echo 'pushing the docker image to dockerhub'
   withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        //login to dockerhub
        sh "echo $PASS |  docker login -u $USER --password-stdin"
        //pushing the image to dockerhub
        sh " docker push $imageName"
    }
}

def deploy() {
   sh "docker compose -f docker-compose.yml down && docker compose -f docker-compose.yml up -d"
}


   
return this
