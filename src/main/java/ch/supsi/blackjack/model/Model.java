package ch.supsi.blackjack.model;


import ch.supsi.blackjack.event.DoneSomethingEvent;

public class Model extends AbstractModel {

    private static Model model;

    protected Model() {
        super();
    }

    // singleton
    public static Model instance() {
        if (model == null) {
            model = new Model();
        }

        return model;
    }

    @Override
    public void doSomething() {
        // do something ...
        System.out.println("I am the model and I am doing something...");

        // finally notify listeners something was done
        pcs.firePropertyChange(new DoneSomethingEvent(this));
    }

}
