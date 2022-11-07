pipeline {
    agent any
    tools
        {
            maven 'M2_HOME'
        }
        environment
        {
        dockerhub = credentials('dockerhub')
        }

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
          stage('Git') {
            steps {
                echo 'Pulling...';
                git branch :'outayel',
                url:'https://github.com/mouradaouiniI/Tpachatmagasin.git',
                credentialsId :'d56e2633-2364-4d69-8cfc-bb82032b8577';
            }
        }
        stage('MAVEN [CLEAN]') {
            steps {
                // mvn -Dmaven.test.failure.ignore=true clean package
                echo "Cleaning...";
                sh "mvn clean -e";

            }
        }
        stage('MAVEN [BUILD]') {
            steps {
                echo "Building...";
                sh "mvn compile -e";
            }
        }
        stage('MAVEN [TEST]') {
            steps {
                echo "Testing...";
                sh "mvn test";
                //sh "mvn test -e";
            }
        }
        stage('SONARQUBE') {
            steps {
                echo "Running SONARQUBE...";
                sh "mvn sonar:sonar -Dsonar.host.url=http://172.10.0.140:9000 -f pom.xml -Dsonar.login=d2a5f43833b4d209a240e4e8ca0259749f837e90";
            }
        }
        stage('NEXUS DEPLOY') {
            steps {
                echo "Deploying...";
                sh "mvn deploy";
                //sh "mvn test -e";
            }
        }
    	stage('Login to Docker Hub') {
                steps
                {
                sh "docker login -u $dockerhub_USR -p $dockerhub_PSW"
                }
                post
                {
                    success
                    {
                        echo 'Docker Hub Login Completed !'
                    }
                }
        }
stage ('Build Image - Docker'){
            steps
            {
               echo 'Starting build Docker image'
                sh "docker build -t outayel/springdevopsapp:1.0.SNAPSHOT ."
            }
            post
            {
                success
                {
                    echo 'Image Build success !'
                }
            }
        }

        stage ('Pushing Image - Docker'){
            steps
            {
                sh "docker push outayel/springdevopsapp"
            }
            post
            {
                success
                {
                    echo 'Image Pushed to Docker hub succeeded !'
                }
            }
        }
    }
}