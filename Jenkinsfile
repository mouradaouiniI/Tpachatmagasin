pipeline {
    agent any

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
                url:'https://github.com/mouradaouiniI/Tpachatmagasin.git';
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
                sh "mvn test -e";
                //sh "mvn test -e";
            }
        }
        stage('SONARQUBE') {
            steps {
                echo "Running SONARQUBE...";
                sh "mvn sonar:sonar -Dsonar.host.url=http://172.10.0.140:9000 -f pom.xml -Dsonar.login=d2a5f43833b4d209a240e4e8ca0259749f837e90";
            }
        }
    }
}