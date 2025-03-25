pipeline {
    agent any  // Runs on any available agent

    // tools {
    //     jdk 'JDK17'  // Ensure JDK 17 is installed in Jenkins
    //     maven 'Maven3'  // Ensure Maven is installed
    // }

    // environment {
    //     SONAR_URL = 'http://your-sonar-server'  // Update with your SonarQube URL
    //     SONAR_TOKEN = credentials('sonar-token')  // SonarQube authentication token stored in Jenkins credentials
    // }

    stages {
        stage('Checkout Code') {
            steps {
                echo "Cloning the repository..."
                git branch: 'main', url: 'https://github.com/manu-devops-sqops/java-project.git'
            }
        }
    
        stage('Clean') {
            steps {
                echo "Running mvn clean..."
                sh 'mvn clean'
            }
        }

        stage('Compile') {
            steps {
                echo "Compiling source code..."
                sh 'mvn compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running tests..."
                sh 'mvn test'
            }
        }

        stage('Verify') {
            steps {
                echo "Running mvn verify..."
                sh 'mvn verify'
            }
        }
        
        stage('Report') {
            steps {
                echo "Running mvn surefire reporting..."
                sh 'mvn surefire-report:report'
            }
        }

        stage('Code Coverage Report') {
            steps {
                echo "Generating JaCoCo report..."
                sh 'mvn jacoco:report'
                jacoco execPattern: 'target/jacoco.exec'
            }
        }

        // stage('SonarQube Analysis') {
        //     steps {
        //         echo "Running SonarQube analysis..."
        //         sh 'mvn sonar:sonar -Dsonar.projectKey=java-project -Dsonar.host.url=$SONAR_URL -Dsonar.login=$SONAR_TOKEN'
        //     }
        // }
    }

    post {
        always {
            echo "Pipeline execution completed!"
        }
        failure {
            echo "Pipeline failed!"
        }
    }
}
