pipeline {
    agent any
	

    environment
    {
    dockerhub = credentials('dockerhub')
    }

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
		stage ('Unit Tests ...') {             
		steps {                  
		sh "mvn test"  }
		}
         stage ('SonarQube') {
            steps {
                sh """mvn sonar:sonar -Dsonar.login=5c08cac7be58cc94cc5adf34897b038dce3fdcc8"""
            }
        }
		stage ('Nexus dep') {
            steps {
                sh """mvn deploy"""
            }
        }
		 stage ('Docker login')
        {
            steps
            {
				sh "docker login -u $dockerhub_USR -p $dockerhub_PSW"
            }
        }
		stage ('Docker build')
        {
            steps
            {
                sh "docker build -t bedis/springdevopsapp:1.0.SNAPSHOT ."
            }
        }
		stage ('Docker push')
        {
            steps
            {
				sh "docker push bedis/springdevopsapp" 
            }
        }

    }
}
