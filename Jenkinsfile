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
          stage('Git') {
            steps {
                echo 'Pulling...';
                git branch :'atef',
                url:'https://github.com/mouradaouiniI/Tpachatmagasin.git',
                credentialsId :'6cec0632-1f25-4114-b902-59899311bcf9';
            }
        }
        stage('Clean maven') {
            steps {
                echo "Cleaning maven";
                sh "mvn clean -e";

            }
        }
        stage('Build maven') {
            steps {
                echo "Building maven";
                sh "mvn compile -e";
            }
        }
        stage('Test maven') {
            steps {
                echo "Testing maven";
                sh "mvn test";
            }
        }
        stage('Sonar') {
            steps {
                echo "Running SONARQUBE...";
                sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.1.16:9000 -f pom.xml -Dsonar.login=77db93415408cdf6dc6e2f6ade3051cb2838eac0";
            }
        }
		stage('Nexus deployment') {
            steps {
                echo "Deploying nexus";
                sh "mvn deploy";
            }
        }
        stage('Docker login') {
                        steps
                        {
                        sh "docker login -u $dockerhub_USR -p $dockerhub_PSW"
                        }
                        post
                        {
                            success
                            {
                                echo 'Logged to docker successfully'
                            }
                        }
                }
        stage ('Docker image build'){
                    steps
                    {
                       echo 'Starting build Docker image'
                        sh "docker build -t atefboutara/springdevopsapp:1.0.SNAPSHOT ."
                    }
                    post
                    {
                        success
                        {
                            echo 'Docker image built successfully'
                        }
                    }
                }

                stage ('Docker image push'){
                    steps
                    {
                        sh "docker push atefboutara/springdevopsapp"
                    }
                    post
                    {
                        success
                        {
                            echo 'Docker image pushed successfully'
                        }
                    }
                }
    }
}