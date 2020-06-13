pipeline {
    agent any
    stages {
        stage('Load') {
            steps {
                load 'C:\\Software\\groovy\\lib\\groovy-xml-2.1.2\\groovy-xml-2.1.2.jar'
            }
        }
        stage('Build') {
            steps {
                def text = '''
        <list>
            <technology>
            <name>Groovy</name>
            </technology>
        </list>
        '''
                def list = new XmlParser().parseText(text)

                assert list instanceof groovy.util.Node
                assert list.technology.name.text() == 'Groovy'
            }
        }
    }
}