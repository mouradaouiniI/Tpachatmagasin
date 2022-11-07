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
 		 stage('MVN BUILD'){
                steps{
                    sh """mvn package -DskipTests"""
                }
            }
                stage('MVN SONARQUBE'){
                    steps{
                  
                        sh """mvn sonar:sonar -Dsonar.login=d59f91156b3ad4822323032bc2376feed8b7145b"""
                  
                }
                    }
stage("BUILD IMAGE TO DOCKER HUB"){
	steps{
sh """docker login -u $dockerhub_USR -p $dockerhub_PSW""";
sh """docker build -t mortadha1222/springdevopsapp:1.0.SNAPSHOT ."""
sh """docker push mortadha1222/springdevsapp"""
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
		    }
		}
 stage('Nexus Deploy'){
                steps{
                    sh """mvn deploy -DskipTests"""
                }
            }
                }

}