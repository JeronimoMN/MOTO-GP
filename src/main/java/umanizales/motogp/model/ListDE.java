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

    public void addEnd(Motorcycle motorcycle) {
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
    }

    public void addBeginning(Motorcycle motorcycle) {
        if (this.head == null) {
            this.head = new NodeDE(motorcycle);
        } else {
            NodeDE newNode = new NodeDE(motorcycle);
            newNode.setNext(this.head);
            this.head.setPrevious(newNode);
            this.head = newNode;

        }
        size++;
    }

    public void addPosition(int position, Motorcycle motorcycle) {
        if (position == 1) {
            addBeginning(motorcycle);
        } else {
            NodeDE temp = this.head;
            NodeDE newNode = new NodeDE(motorcycle);
            int sitio = 1;

            while (sitio < (position - 1)) {
                temp = temp.getNext();
                sitio++;
            }

            newNode.setNext(temp.getNext());
            newNode.setPrevious(temp);

            if (temp.getNext() != null) {
                temp.getNext().setPrevious(newNode);
            }
            temp.setNext(newNode);
            this.size++;
        }
    }

    public boolean advance(String pilot, int num) {
        NodeDE temp = this.head;
        int count = 1;

        while (!Objects.equals(temp.getData().getPilot(), pilot)) {
            temp = temp.getNext();
            count++;
        }
        if (num < count) {
            addPosition(count - num, temp.getData());
            temp = temp.getPrevious();
            temp.setNext(temp.getNext().getNext());

            if (temp.getNext() != null) {
                temp.getNext().setPrevious(temp);
            }
            this.size--;
            return true;
        }
        return false;
    }

    public boolean losePosition(String pilot, int num) {
        NodeDE temp = this.head;
        int count = 1;

        while (!Objects.equals(temp.getData().getPilot(), pilot)) {
            temp = temp.getNext();
            count++;
        }

        if ((count + num) <= this.size) {

            //ELIMINAR  EL NODO ORIGINAL

            if (temp.getPrevious() != null) {
                //Si hay algo atras, lo enlazo con lo de adelante
                temp.getPrevious().setNext(temp.getNext());
            } else {
                //Si antes del temporal no hay nada, quiere decir que es la cabeza.
                this.head = this.head.getNext();
            }

            if (temp.getNext() != null) {
                //Si hay algo adelante, lo enlazo con lo de atras
                temp.getNext().setPrevious(temp.getPrevious());
            }

            size--;
            //--------------------------------------------------
            //Si queda de ultimo en la carrera
            if ((count + num) == this.size + 1) {
                addEnd(temp.getData());
            }
            //Si pierde n posiciones.
            else if ((count + num) < this.size + 1) {
                addPosition((count + num), temp.getData());
            }
            return true;
        }
        return false;
    }

    public int deletePilot(String pilot){
        int count = 0;

        if(this.head != null){

            //Si esta en la cabeza
            if(Objects.equals(this.head.getData().getPilot(), pilot)){
                this.head = this.head.getNext();
                count++;
            }

            //Si no
            else{
                count = 1;
                NodeDE temp = this.head;
                while(true){
                    if(Objects.equals(temp.getNext().getData().getPilot(), pilot)){
                        temp.setNext(temp.getNext().getNext());
                        if(temp.getNext() != null){
                            temp.getNext().setPrevious(temp);
                        }
                        count++;
                        size--;
                        break;
                    }
                    temp= temp.getNext();
                    count= count+1;
                }
            }
        }
        return count;
    }
}
