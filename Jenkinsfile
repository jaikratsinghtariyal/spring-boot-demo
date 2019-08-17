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
				
			
				withCredentials([[$class: 'usernamePassword', 
									credentialsId:'PCF_ID',
  									usernameVariable: 'USERNAME', 
  									passwordVariable: 'PASSWORD']]) {
					
					echo $USERNAME
					echo $PASSWORD
				}
			}
		}
		
	}
}