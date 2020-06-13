
load 'C:\\Software\\groovy\\lib\\groovy-xml-2.1.2\\groovy-xml-2.1.2.jar'
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
