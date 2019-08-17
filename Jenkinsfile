pipeline {

	agent any
	tools {
        maven 'Maven'
        jdk 'JDK8'
    }
    
	stages {
	
		stage ('Build') {
			steps {
					bat 'mvn clean package -DskipTests' 
			}
			
		}
		
		stage ('Deploy') { 
			steps  {
				withCredentials([[$class: 'UsernamePasswordMultiBinding', 
									credentialsId:'PCF_ID',
  									usernameVariable: 'USERNAME', 
  									passwordVariable: 'PASSWORD']]) {
					bat 'cf auth $USERNAME $PASSWORD'
					bat 'cf target -o tektutorial'
					bat 'cf push spring-boot-demo --random-route ' 
				}
			}
		}
		
	}
}