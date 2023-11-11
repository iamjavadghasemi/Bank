import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class LinkedList<E> {
    private Node<E> first = null;
    private Node<E> last = null;
    private int length = 0;
    private int counter = 0;
    public Node<E> getFirst() {
        return first;
    }
    public int getLength() {
        return length;
    }
    public int getCounter() {
        return counter;
    }
    public void insertToFirst(E element) {
        Node<E> node = new Node<>(null, element, null);
        if(first == null) {
            last = node;
        } else {
            first.setPrevious(node);
            node.setNext(first);
        }
        first = node;
        length++;
        counter++;
    }
    public void insertToLast(E element) {
        Node<E> node = new Node<>(null, element, null);
        if(last == null) {
            first = node;
        } else {
            last.setNext(node);
            node.setPrevious(last);
        }
        last = node;
        length++;
        counter++;
    }
    public void remove(E element) throws Exception {
        Node<E> node = first;
        while(node != null) {
            if(node.getElement().equals(element)) {
                break;
            }
            node = node.getNext();
        }
        if(node != null) {
            if(first == last) {
                first = null;
                last = null;
            } else if(node == first) {
                node.getNext().setPrevious(null);
                first = node.getNext();
            } else if(node == last) {
                node.getPrevious().setNext(null);
                last = node.getPrevious();
            } else {
                node.getPrevious().setNext(node.getNext());
                node.getNext().setPrevious(node.getPrevious());
            }
            length--;
        } else {
            throw new Exception("There is no such member.");
        }
    }
    public E find(E element) throws Exception {
        Node<E> node = first;
        while(node != null) {
            if(node.getElement().equals(element)) {
                break;
            }
            node = node.getNext();
        }
        if(node != null) {
            return node.getElement();
        } else {
            throw new Exception("There is no such member.");
        }
    }
    public void save(File file) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            Node<E> node = first;
            while(node != null) {
                if(node.getElement() instanceof Person) {
                    writer.write(node.getElement().toString()+"/"+counter+"\n");
                } else if(node.getElement() instanceof Account) {
                    writer.write(node.getElement().toString()+"/"+counter+"\n");
                }
                node = node.getNext();
            }
        } catch(IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch(IOException exception) {
                exception.printStackTrace();
            }
        }
    }
    public void load(File file, LinkedList<Customer> customers) {
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            int number;
            String string = new String();
            while((number = reader.read()) != -1) {
                string+=(char) number;
            }
            String[] all = string.split("\n");
            for(String elements:all) {
                String[] parts = elements.split("/");
                if(Long.parseLong(parts[0]) == 10000000) {
                    insertToLast((E) new Manager(Long.parseLong(parts[0]), parts[1], parts[2], Long.parseLong(parts[3])));
                    counter = Integer.parseInt(parts[4]);
                } else if(Long.parseLong(parts[0]) >= 11000000 && Long.parseLong(parts[0]) < 12000000) {
                    insertToLast((E) new Employee(Long.parseLong(parts[0]), parts[1], parts[2], Long.parseLong(parts[3])));
                    counter = Integer.parseInt(parts[4]);
                } else if(Long.parseLong(parts[0]) >= 12000000 && Long.parseLong(parts[0]) < 98000000) {
                    insertToLast((E) new Customer(Long.parseLong(parts[0]), parts[1], parts[2], Long.parseLong(parts[3]), Long.parseLong(parts[4])));
                    counter = Integer.parseInt(parts[5]);
                } else {
                    try {
                        Account account = new Account(Long.parseLong(parts[0]), Long.parseLong(parts[1]), customers.find(new Customer(Long.parseLong(parts[4]))));
                        account.setIndexHistory(Integer.parseInt(parts[3]));
                        String[] allHistory = parts[2].split(", ");
                        for(String history:allHistory) {
                            account.setHistory(history);
                        }
                        insertToLast((E) account);
                        counter = Integer.parseInt(parts[5]);
                    } catch(Exception exception) {
                        return;
                    }
                }
            }
        } catch(IOException | NumberFormatException exception) {
            return;
        } finally {
            try {
                reader.close();
            } catch(IOException exception) {
                exception.printStackTrace();
            }
        }
    }
    public void print() throws Exception {
        if(length != 0) {
            Node<E> node = first;
            int count = 0;
            while(node != null) {
                count++;
                if(node.getElement() instanceof Person) {
                    System.out.println(count+". "+((Person) node.getElement()).getInformation());
                } else if(node.getElement() instanceof Account) {
                    System.out.println(count+". "+((Account) node.getElement()).getInformation());
                }
                node = node.getNext();
            }
        } else {
            throw new Exception("There is no member.");
        }
    }
}