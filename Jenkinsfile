pipeline {
    agent any

    stages {
        stage('Git steps') {
            steps {
                echo 'Working'
                git branch : 'bedis',
                url : 'https://github.com/mouradaouiniI/Tpachatmagasin.git'
            }
        }
        stage ('Mvn clean') {
            steps {
                sh"""mvn clean"""
            }
        }
        stage ('Mvn Compile') {
            steps {
                sh"""mvn compile"""
            }
        }
        stage ('Mvn build') {
            steps {
                sh"""mvn package -DskipTests"""
            }
        }
         stage ('SonarQube') {
            steps {
                sh """mvn sonar:sonar -Dsonar.login=5c08cac7be58cc94cc5adf34897b038dce3fdcc8"""
            }
        }
    }
}
