def gv
pipeline {
    agent any
    tools {
     maven  'maven'
    }
       environment {
           NEXUS_URL="13.41.247.78:8081"
           NEXUS_VERSION = "nexus3"
           NEXUS_PROTOCOL = "http"
           NEXUS_REPOSITORY = "devops"
           NEXUS_CREDENTIAL_ID = "nexus-user-credentials"
           IMAGE_NAME="leithmhf/devops:${BUILD_NUMBER}"
        }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "functions.groovy"
                }
            }
        }
        
        stages {
        stage("unit tests") {
            steps {
                script {
                    gv.unitTest()
                }
            }
        }
        
         stage("sonar test") {
            steps {
                script {
                   
                sh 'mvn sonar:sonar \
                   -Dsonar.projectKey=devop \
                   -Dsonar.host.url=http://18.130.219.36:9000 \
                   -Dsonar.login=c6b7d8e141bf20018e908762382a4649baac9696'              }
            }
        }
        
        stage("get code") {
            steps {
                script {
                   
                 gv.cloneCode()
                }
            }
        }
        
         stage("Build artifact") {
            steps {
                script {
                   
                 gv.buildJar()
                }
            }
        }
        stage("Publish to Nexus Repository Manager") {
            steps {
                script {
                    
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
            }
        }
              stage("build docker image and push to dockerhub") {
            steps {
                script {
                  
                   gv.buildImage(env.IMAGE_NAME)
                }
            }
        }
    }
}
