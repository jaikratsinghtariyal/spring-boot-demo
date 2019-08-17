pipeline {

	agent any
	tools {
        maven 'Maven'
        jdk 'JDK8'
    }
    
	stages {
	
		stage ('Initialize') {
            steps {
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
            }
        }
		stage ('Build') {
			steps {
					bat 'mvn clean package' 
			}
			
		}
		
		stage ('Deploy') { 
			steps  {
				withCredentials([[$class: 'UsernamePasswordMultiBinding', 
									credentialsId:'PCF_ID',
  									usernameVariable: 'USERNAME', 
  									passwordVariable: 'PASSWORD']]) {
					bat 'cf login -a http://api.run.pivotal.io -u $USERNAME -p $PASSWORD'
					bat 'cf push spring-boot-demo --random-route ' 
				}
			}
		}
		
	}
}