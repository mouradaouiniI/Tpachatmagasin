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
                        sh "docker build -t atefboutara/springdevopsapp:1.0.SNAPSHOT ."
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
                        sh "docker push atefboutara/springdevopsapp"
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