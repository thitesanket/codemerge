@Library('groovy-xml@2.1.2')

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
