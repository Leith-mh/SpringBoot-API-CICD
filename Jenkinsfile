def gv
pipeline {
    agent any
    tools {
     maven  'maven'
    }
       environment {
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
