package umanizales.motogp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class ListDE {
    private NodeDE head;
    private int size;

    public List<Motorcycle> getListMotorcycles() {
        List<Motorcycle> motorcycles = new ArrayList<>();
        NodeDE temp = head;
        while (temp != null) {
            motorcycles.add(temp.getData());
            temp = temp.getNext();
        }
        return motorcycles;
    }

    public String addEnd(Motorcycle motorcycle) {
        NodeDE newNode = new NodeDE(motorcycle);
        if (head == null) {
            head = newNode;
        } else {
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
            newNode.setPrevious(temp);
        }
        size++;
        return "Motorcycle added at the end";
    }

    public String addBeggining(Motorcycle motorcycle) {
        NodeDE newNode = new NodeDE(motorcycle);
        if (head == null) {
            head = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            this.head = newNode;
        }
        size++;
        return "Agredada al inicio";
    }

    public int deletePilot(String pilot) {
        int count = 0;
        if (head != null) {
            if (Objects.equals(head.getData().getPilot(), pilot)) {
                head = head.getNext();
                size--;
                count++;
            } else {
                count = 1;
                NodeDE temp = head;
                while (true) {
                    if (Objects.equals(temp.getNext().getData().getPilot(), pilot)) {
                        temp.setNext(temp.getNext().getNext());
                        if (temp.getNext() != null) {
                            temp.getNext().setPrevious(temp);
                        }
                        count++;
                        size--;
                        break;
                    }
                    temp = temp.getNext();
                    count = count + 1;

                }
            }
        }
        return count;
    }

    public void addPosition(int position, Motorcycle motorcycle) {
        if (position == 1) {
            addBeggining(motorcycle);
        } else {
            NodeDE temp = head;
            int place = 1;
            while (place < (position - 1)) {
                temp = temp.getNext();
                place += 1;
            }
            NodeDE newNode = new NodeDE(motorcycle);
            newNode.setPrevious(temp);

            if (temp.getNext() != null) {
                newNode.setNext(temp.getNext());
                temp.getNext().setPrevious(newNode);
            }
            temp.setNext(newNode);
            size++;
        }
    }

    public boolean advance(String pilot, int num) {
        if (size > 1) {
            int count = 1;
            NodeDE temp = head;
            while (!Objects.equals(temp.getData().getPilot(), pilot)) {
                count++;
                temp = temp.getNext();
            }
            if (num < count) {
                addPosition((count - num), temp.getNext().getData());
                temp = temp.getPrevious();
                temp.setNext(temp.getNext().getNext());
                if (temp.getNext() != null) {
                    temp.getNext().setPrevious(temp);
                }
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean losePosition(String pilot, int num) {
        if (size > 1) {
            int count = 1;
            NodeDE temp = head;
            while (!Objects.equals(temp.getData().getPilot(), pilot)) {
                count++;
                temp = temp.getNext();
            }
            if ((count + num) <= size) {

                if ((count + num) == size) {
                    addEnd(temp.getData());
                } else if ((count + num) < size) {
                    addPosition((count + num), temp.getData());
                }
                if (head == temp) {
                    head = head.getNext();
                } else {
                    temp = temp.getPrevious();
                    temp.setNext(temp.getNext().getNext());
                    temp.getNext().setPrevious(temp);
                    return true;
                }
            }
        }
        return false;
    }
}
