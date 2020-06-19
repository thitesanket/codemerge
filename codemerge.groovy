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
                    new File('.').eachFileRecurse(FILES) {
                        if(it.name.endsWith('.xml')) {
                            println it
                        }
                    }
                    dir('src') {
                        echo "Hello World!"

                        def xml1 = """<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.0.RELEASE</version>
    </parent>
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.example</groupId>
	<artifactId>LoadRunProp</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>LoadRunProp</name>
	<description>Demo project for Spring Boot</description>
</project>	
"""
                        def pom = new XmlParser().parseText(xml1)
                        echo "parent version " + pom['parent']['version'].text()
                        def pomMaven = readMavenPom file: 'pom.xml'
                        echo "Maven " + pomMaven
                        def props =  pomMaven.getProperties()
                        def parent = pomMaven.getParent()
                        echo props.stylezoo
                        echo parent.version
                        props.stylezoo = "5.0"
                        parent.version = "5.0"
                        pomMaven.setProperties(props)
                        pomMaven.setParent(parent)
                        writeMavenPom model:pomMaven
                    }
                }
            }
        }
    }
}