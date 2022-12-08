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
         
        stage("Clone Repository") {
            steps {
                script {

                 gv.cloneCode()
                }
            }
        }
             
         stage("Unit Tests") {
             steps {
                 script {
                     gv.unitTest()
                 }
             }
         }        

         stage("Build Artifact") {
            steps {
                script {

                 gv.buildJar()
                }
            }
        }
        stage("Publish Artifact to Nexus Repository Manager") {
            steps {
                script {

                    gv.pushImageNexus(env.NEXUS_VERSION,env.NEXUS_PROTOCOL,env.NEXUS_URL,env.NEXUS_REPOSITORY,env.NEXUS_CREDENTIAL_ID)
                      
                }
            }
        }
              stage("build docker image") {
            steps {
                script {

                   gv.buildImage(env.IMAGE_NAME)
                }
            }
        }
        
        stage("Publish Docker Image to DockerHub") {
            steps {
                script {
                    gv.pushImageDocker(env.IMAGE_NAME)
                }
            }
        }
       
        stage("deploy API") {
            steps {
                script {
                    gv.deploy()
                }
            }
        }
    }
}
