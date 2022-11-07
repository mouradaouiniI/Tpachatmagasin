pipeline {
    agent any
tools {
        maven "M2_HOME"
    }
    stages {
        stage('Checkout GIT'){
            steps {
                echo 'Pulling... ';
                git branch: 'mortadha',
                url : 'https://github.com/mouradaouiniI/Tpachatmagasin.git';
            }
        }
           
            stage('Testing maven'){
                steps {
                    sh """mvn -version"""
                }
            }
            stage('MVN CLEAN'){
                steps{
                    sh """mvn clean"""
                }
            }
                stage('MVN COMPILE'){
                    steps{
                        sh """mvn compile"""
                    }
                }
                stage('MVN SONARQUBE'){
                    steps{
                  
                        sh """mvn sonar:sonar -Dsonar.login=bd98870eb40a162d3342da7b624f93fa8c021241"""
                  
                }
                    }
                }
post {
    always {
      junit(
        allowEmptyResults: true,
        testResults: '**/test-reports/*.xml'
      )
    }
            }
