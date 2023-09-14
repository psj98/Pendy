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

        stage('FE-Install'){
            steps{
                sh "echo '##### FE INSTALL START#####'"
                dir('frontend') {
                    nodejs('18.16.1'){
                        sh 'npm install'
                    }
                }
                sh "echo '##### FE INSTALL SUCCESS #####'"
            }
        }

        stage('FE-Test'){
            steps {
                sh "echo '##### FE TEST START #####'"
                dir('frontend'){
                    sh 'npm run test'
                }
                sh "echo '##### FE TEST SUCCESS #####'"
            }
        }

        stage('BE-Test'){
            steps{
                sh "echo '##### BE TEST START #####'"
                dir('backend/namani'){
                    sh './gradlew clean test'
                }
                sh "echo '##### BE TEST SUCCESS #####'"
            }
        }

        stage('FE-Build'){
            steps{
                sh "echo '##### FE BUILD START #####'"
                dir('frontend'){
                    sh 'npm run build'
                }
                sh "echo '##### FE BUILD SUCCESS #####'"
            }
        }

        stage('BE-Build'){
            steps {
                sh "echo '##### BE BUILD START #####'"
                dir('backend/namani') {
                    sh './gradlew bootJar'
                }
                sh "echo '##### BE BUILD SUCCESS #####'"
            }
        }

        stage('FE-Deploy'){
            steps{
                sshagent(credentials:['ubuntu']){
                    sh "echo '##### FE DEPLOY START #####'"
                    dir('frontend'){
                        sh "scp -o StrictHostKeyChecking=no -i ${env.FRONT_KEY_FILE} -r dist ubuntu@${env.DEV_BACK_IP}:/home/ubuntu"
                    }
                    sh "ssh -o StrictHostKeyChecking=no -i ${env.FRONT_KEY_FILE} ubuntu@${env.DEV_BACK_IP} 'sudo service nginx restart'"
                    sh "echo '##### FE DEPLOY SUCCESS #####'"
                    sh "echo '##### FE COMPLETE #####'"
                }
            }
        }

        stage('BE-Deploy'){
            steps{
                sshagent(credentials:['ubuntu']){
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
