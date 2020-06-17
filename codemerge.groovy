pipeline {
    agent any
    parameters {
        string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')

        text(name: 'BIOGRAPHY', defaultValue: '', description: 'Enter some information about the person')

        booleanParam(name: 'TOGGLE', defaultValue: true, description: 'Toggle this value')

        choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')

        password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
    }
    stages {
        stage('Example') {
            steps {
                echo "Hello ${params.PERSON}"

                echo "Biography: ${params.BIOGRAPHY}"

                echo "Toggle: ${params.TOGGLE}"

                echo "Choice: ${params.CHOICE}"

                echo "Password: ${params.PASSWORD}"
            }
        }
        stage('Build') {
            steps {
                script {
                    dir('src') {
                        println "Hello World!"

                        def xml1 = """<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.3.0.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example</groupId>
	<artifactId>LoadRunProp</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>LoadRunProp</name>
	<description>Demo project for Spring Boot</description>
</project>	
"""
                        def pom = new XmlParser().parseText(xml1)

                        println "Parent Version " + pom.parent.version.text()
                        println "Artifact Version " + pom.version.text()
                        def atrVer =  pom.version[0]
                        def parentVer = pom.parent.version[0]

/*pom.depthFirst().find{it -> it.name() == 'version' && it.parent().name()!='parent'}.value='5.0-SNAPSHOT'*/
                        parentVer.value  = '5.0-SNAPSHOT'
                        atrVer .value='5.1'

                        println "After replace"
                        println "Artifact Version " + pom.version.text()
                        println "Parent Version " + pom.parent.version.text()

                        def pom1 = readFile 'pom.xml'
                        def list = new XmlParser().parseText(pom1)
                        def response = parser.parseText(xml)
                        echo response.value.toString()
                    }
                }
            }
        }
    }
}