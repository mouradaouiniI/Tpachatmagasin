pipeline {
    agent any
tools {
        maven "M2_HOME"
    }
  /*  environment
    {
	dockerhub = credentials('dockerhub')
    }*/
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
                    sh """mvn build -DskipTests"""
                }
            }
                stage('MVN SONARQUBE'){
                    steps{
                  
                        sh """mvn sonar:sonar -Dsonar.login=bd98870eb40a162d3342da7b624f93fa8c021241"""
                  
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
                }

}