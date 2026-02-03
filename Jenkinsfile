pipeline {
    agent any

    tools {
        maven 'maven3'
    }

    stages {

        stage('Clone') {
            steps {
                git 'https://github.com/Manoj18121812/CICD-CRUD-MailSending.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t cicd-app .'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                docker rm -f cicd || true
                docker run -d -p 8081:8080 --name cicd cicd-app
                '''
            }
        }
    }
}

