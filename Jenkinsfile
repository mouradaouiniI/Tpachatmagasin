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
                git branch :'atef',
                url:'https://github.com/mouradaouiniI/Tpachatmagasin.git',
                credentialsId :'6cec0632-1f25-4114-b902-59899311bcf9';
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
    }
}