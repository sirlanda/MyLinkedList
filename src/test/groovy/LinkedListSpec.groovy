import hu.benya77.mylinkedlist.MyLinkedList
import spock.lang.Specification

class LinkedListSpec extends Specification {

    def "Az új lista üres"() {
        given:
        MyLinkedList<Integer> list = new MyLinkedList<>()

        expect:
        list.size() == 0
    }

    def "Ha elemet adok a listához, akkor a mérete nő"() {
        given:
        MyLinkedList<Integer> list = new MyLinkedList<>()

        when:
        list.add(10)

        then:
        list.size() == 1
    }

    def "Új elem a lista végére kerül"() {
        given:
        MyLinkedList<Integer> list = new MyLinkedList<>()
        list.add(10)

        def secondElement = 20
        when:
        list.add(secondElement)

        then:
        list.size() == 2
        list.get(1) == secondElement
    }

    def "A hozzáadott elemet tartalmazza a lista"() {
        given:
        MyLinkedList<Integer> list = new MyLinkedList<>()
        list.add(10)

        def secondElement = 20
        when:
        list.add(secondElement)

        then:
        list.contains(20)
        !list.contains(30)
    }

}
