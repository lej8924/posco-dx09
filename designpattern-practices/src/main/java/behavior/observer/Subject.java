package behavior.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject<T> {

    private T val;
    private List<Observer<T>> observerList = new ArrayList<>();

    public void registerObserver(Observer<T> observer){
        observerList.add(observer);
    }
    public void removeObserver(Observer<T> observer){
        observerList.remove(observer);
    }

    public void changeSubject(T val){
        this.val = val;
        notifyObservers();
    }
    private void notifyObservers(){
        for(Observer<T> observer: observerList){
            observer.update(val);
        }
    }
}
