def gv
pipeline {
    agent any
    tools {
     maven  'maven'
    }
       environment {
           NEXUS_URL="http://13.41.247.78:8081"
        }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "functions.groovy"
                }
            }
        }
    }
}
