pipeline {

	agent any
	tools {
        maven 'Maven 3.6.0'
        jdk 'jdk8'
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
				withMaven(maven: 'maven_3_5_0') {
					sh 'mvn clean package' 
				}
			}
			
		}
		
		stage ('Deploy') { 
			steps  {
				withCredentials([[$class: 'UsernamePasswordMultiBinding', 
									credentialsId:'PCF_ID',
  									usernameVariable: 'USERNAME', 
  									passwordVariable: 'PASSWORD']]) {
					sh 'cf login -a http://api.run.pivotal.io -u $USERNAME -p $PASSWORD'
					sh 'cf push spring-boot-demo --random-route ' 
				}
			}
		}
		
	}
}