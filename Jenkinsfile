pipeline {

	agent any
	tools {
        maven 'Maven'
        jdk 'JDK8'
    }
    
    
	stages {
		stage ('Build') {
			steps {
					sh 'mvn clean package -DskipTests' 
			}
		}
		
		stage ('Deploy') { 
			steps  {			
				withCredentials([[$class: 'UsernamePasswordMultiBinding', 
									credentialsId:'PCF_ID',
  									usernameVariable: 'USERNAME', 
  									passwordVariable: 'PASSWORD']]) {
					sh '/usr/local/bin/cf login -a http://api.run.pivotal.io -u %USERNAME% -p %PASSWORD%'
					sh '/usr/local/bin/cf push spring-boot-demo --random-route ' 
				}
			}
		}
		
	}
}