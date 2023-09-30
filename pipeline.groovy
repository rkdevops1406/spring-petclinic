pipeline{
    agent any
    stages{
        stage("code"){
            steps{
               git branch: 'main', credentialsId: 'gitcred', url: 'https://github.com/rkdevops1406/spring-petclinic.git'
            }
        }
        stage("Build"){
            steps{
                
                sh 'mvn clean package'
                               
            }
        }
        stage("Deploy to tomcat"){
            steps{
                sshagent(['3dc1c3c3-26d4-46dd-9cd2-2fc62facaafc']) {
                sh 'scp -o StrictHostKeyChecking=no /var/lib/jenkins/workspace/Pipeline-1/target/maven-web-application.war  ec2-user@18.191.119.167:/root/apache-tomcat-9.0.80/webapps'   
    
                }
            }
        }
    }  
}   
