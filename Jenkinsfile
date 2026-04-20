pipeline {
    agent any

    tools {
        jdk 'jdk21'
        maven 'maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'deploy',
                    url: 'https://github.com/LuisPerezcx/INESIS-BACKEND'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                cp target/*.jar /opt/inesis-backend/INESIS-BACKEND.jar
                sudo systemctl restart inesis-backend
                '''
            }
        }
    }
}
