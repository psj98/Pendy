pipeline{
    agent any

    tools {
        gradle 'gradle'
    }

    stages{
        stage('Clone'){
            steps{
                sh "echo 'Clone'"
                git branch: 'dev',
                        credentialsId: 'gitlabID',
                        url: 'https://lab.ssafy.com/s09-fintech-finance-sub2/S09P22A410.git'
                sh "echo '####### CLONE DEV #######'"
            }
        }

        // fe 

        stage('BE-Test'){
            steps{
                sh "echo '##### BE TEST START #####'"
                dir('./backend/namani'){
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean test'
                }
                sh "echo '##### BE TEST SUCCESS #####'"
            }
        }

        // fe build

        stage('BE-Build'){
            steps {
                sh "echo '##### BE BUILD START #####'"
                dir('backend/namani') {
                    sh './gradlew bootJar'
                }
                sh "echo '##### BE BUILD SUCCESS #####'"
            }
        }

        // fe deploy

        stage('BE-Deploy'){
            steps{
                sshagent(credentials:['ec2-user']){
                    sh "echo '##### BE DEPLOY START #####'"
                    sh "ssh -o StrictHostKeyChecking=no ubuntu@j9a410.p.ssafy.io uptime"
                    sh "scp /var/jenkins_home/workspace/namani/backend/namani/build/libs/namani-0.0.1-SNAPSHOT.jar ubuntu@j9a410.p.ssafy.io:/home/ubuntu/namani"
                    sh "ssh -t ubuntu@j9a410.p.ssafy.io ./deploy.sh"
                    sh "echo '###### BE DEPLOY SUCCESS ######'"
                    sh "echo '###### BE COMPLETE ######'"
                }
            }
        }
    }
}
