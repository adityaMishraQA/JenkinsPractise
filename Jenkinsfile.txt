pipeline{
    agent any
    stages{
        stage('1st try'){
            steps{
                echo "Running 1 try task......"
            }
        }
        stage('BesantProject'){
            steps{
                echo "Running besan...."
            }
        }
    }
}