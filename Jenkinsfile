pipeline {

	agent any
	
	stages {
	
		stage ('Build') {
			steps {
				'mvn clean package' 
			}
			
		}
		
		stage ('Deploy') { 
			steps  {
				withCredentials([[$class: 'UsernamePasswordMultiBinding', 
									credentialsId:'PCF_ID',
  									usernameVariable: 'USERNAME', 
  									passwordVariable: 'PASSWORD']]) {
					'cf login -a http://api.run.pivotal.io -u $USERNAME -p $PASSWORD'
					'cf push spring-boot-demo --random-route ' 
				}
			}
		}
		
	}
}