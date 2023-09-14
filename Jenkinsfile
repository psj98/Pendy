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
                    dir('backend/namani/build/libs'){
                        sh "scp -o StrictHostKeyChecking=no -i ${env.BACK_KEY_FILE} ubuntu@${env.DEV_BACK_IP}:/home/ubuntu/namani"
                    }
                    sh "ssh -o StrictHostKeyChecking=no -i ${env.BACK_KEY_FILE} ubuntu@${env.DEV_BACK_IP} 'cd namani && ./deploy.sh'"
                    sh "echo '###### BE DEPLOY SUCCESS ######'"
                    sh "echo '###### BE COMPLETE ######'"
                }
            }
        }
    }
}
