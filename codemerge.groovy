pipeline {
    agent any
    stages {
        stage('Load') {
            steps {
                load 'C:\\Software\\groovy\\lib\\groovy-xml-2.1.2\\groovy-xml-2.1.2.jar'
            }
        }
    }
}