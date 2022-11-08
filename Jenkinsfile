pipeline {
    agent any
tools {
        maven "M2_HOME"
    }
   environment
    {
	dockerhub = credentials('dockerhub')
    }
    stages {
        stage('Checkout GIT'){
            steps {
                echo 'Pulling... ';
                git branch: 'mortadha',
                url : 'https://github.com/mouradaouiniI/Tpachatmagasin.git';
            }
        }
           
            stage('MVN CLEAN'){
                steps{
                    sh """mvn clean"""
                }
            
		post {
        failure {
            emailext body: 'Mvn Clean failure', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Pipeline Failure'
        }
    }
            }
                stage('MVN COMPILE'){
                    steps{
                        sh """mvn compile"""
                    }
                
		post {
        failure {
            emailext body: 'Mvn Compile failure', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Pipeline Failure'
        }
    }
                }
 		 stage('MVN BUILD'){
                steps{
                    sh """mvn package -DskipTests"""
                }
            }
		post {
        failure {
            emailext body: 'Mvn Build failure', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Pipeline Failure'
        }
    }
                                	stage ('Unit Tests ...')
		{
		    steps
		    {
		        echo 'Unit Tests Start ...'
		        sh "mvn test"
		    }

		    post
		    {
		        success
		        {
		            echo 'Unit Tests Complete ...'
		        }
					
        failure {
            emailext body: 'Mvn Tests failure', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Pipeline Failure'
        }
    
		    }
		}
                stage('MVN SONARQUBE'){
                    steps{
                  
                        sh """mvn sonar:sonar -Dsonar.login=ce8010d4fef2b30237f1b82d5961c688e26e1640"""
                  
                }
		post {
        failure {
            emailext body: 'SonarQube failure', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Pipeline Failure'
        }
    }
                    }
	 stage('Nexus Deploy'){
                steps{
                    sh """mvn deploy -DskipTests"""
                }
            
		post {
        failure {
            emailext body: 'Nexus Deploy failure', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Pipeline Failure'
        }
    }
 }
			stage("BUILD IMAGE TO DOCKER HUB"){
				steps{
					sh """docker login -u $dockerhub_USR -p $dockerhub_PSW""";
					sh """docker build -t mortadha1222/springdevopsapp:1.0.SNAPSHOT ."""
					sh """docker push mortadha1222/springdevopsapp"""
					}
		post {
        failure {
            emailext body: 'Mvn Build failure', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Pipeline Failure'
        }
    }
					}
			/*stage("DOCKER COMPOSE UP"){
					steps{
						sh """docker-compose -f docker-compose.yml up""";
						}
					}*/


                }

}