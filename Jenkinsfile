pipeline {
    agent any
	

    environment
    {
    dockerhub = credentials('dockerhub')
    }

    stages {
        stage('Project update from gitHub ') {
            steps {
                echo 'Pulling Project...'
                git branch : 'bedis',
                url : 'https://github.com/mouradaouiniI/Tpachatmagasin.git'
				echo 'Done!'
              
            }
        }
		
        stage ('Maven clean') {
            steps {
            	echo 'Working on Maven clean...'
                sh"""mvn clean"""
				echo 'Maven Clean Done!'
            }
        }
		
        stage ('Maven Compile') {
            steps {
            	echo 'Compiling project...'
                sh"""mvn compile"""
				echo 'Compiling Done!'
            }
        }
		
        stage ('Maven build') {
            steps {
            	echo 'Building project...'
                sh"""mvn package -DskipTests"""
				echo 'Building Done!'
            }
        }
		
		stage ('Unit Tests') {             
		steps {
		    echo 'Running Unit Tests...'              
			sh "mvn test"  }
			echo 'Done!'
		}
		
         stage ('SonarQube') {
            steps {
            	echo 'Checking code quality...'
                sh """mvn sonar:sonar -Dsonar.login=5c08cac7be58cc94cc5adf34897b038dce3fdcc8"""
				echo 'Done!'
            }
        }
		
		stage ('Nexus Deployment') {
            steps {
            	echo 'Organizing and Storeing Artifacts...'
                sh """mvn deploy"""
				echo 'Done!'
            }
        }
		
		 stage ('Docker Login')
        {
            steps
            {
            	echo 'Logging to DockerHub...'
				sh "docker login -u $dockerhub_USR -p $dockerhub_PSW"
				echo 'Done!'
            }
        }
		
		stage ('Docker Build')
        {
            steps
            {
            	echo 'Building Docker Image...'
                sh "docker build -t bedis/springdevopsapp:1.0.SNAPSHOT ."
				echo 'Done!'
            }
        }
		
		stage ('Docker Push')
        {
            steps
            {
            	echo 'Pushing Docker Image To DockerHub...'
				sh "docker push bedis/springdevopsapp" 
				echo 'Done!'

				
            }
        }

    }
}
