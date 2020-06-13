node {
    load 'C:\\Work\\Java\\codemerge\\codemerge.groovy'
}
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
