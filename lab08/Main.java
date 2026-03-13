import java.util.*;

/* MODEL */

enum SeatStatus {
    DISPONIVEL,
    RESERVADO,
    INDISPONIVEL
}

/* EVENT */

class SeatEvent {

    private int seatNumber;
    private SeatStatus status;

    public SeatEvent(int seatNumber, SeatStatus status) {
        this.seatNumber = seatNumber;
        this.status = status;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public SeatStatus getStatus() {
        return status;
    }
}

/* LISTENER (Observer) */

interface SeatListener {

    void seatChanged(SeatEvent event, BusModel source);

}

/* SOURCE (Model principal) */

class BusModel {

    private Map<Integer, SeatStatus> seats = new HashMap<>();
    private List<SeatListener> listeners = new ArrayList<>();

    public BusModel(int totalSeats) {

        for (int i = 1; i <= totalSeats; i++) {
            seats.put(i, SeatStatus.DISPONIVEL);
        }

    }

    public void addListener(SeatListener listener) {
        listeners.add(listener);
    }

    public void removeListener(SeatListener listener) {
        listeners.remove(listener);
    }

    public void reserveSeat(int seat) {

        seats.put(seat, SeatStatus.RESERVADO);

        notifyListeners(new SeatEvent(seat, SeatStatus.RESERVADO));
    }

    public void buySeat(int seat) {

        seats.put(seat, SeatStatus.INDISPONIVEL);

        notifyListeners(new SeatEvent(seat, SeatStatus.INDISPONIVEL));
    }

    public Map<Integer, SeatStatus> getSeats() {
        return seats;
    }

    private void notifyListeners(SeatEvent event) {

        for (SeatListener listener : listeners) {
            listener.seatChanged(event, this);
        }

    }

}

/* VIEW */

/* Painel Central */

class CentralPanelView implements SeatListener {

    @Override
    public void seatChanged(SeatEvent event, BusModel source) {

        System.out.println("\n===== PAINEL CENTRAL =====");

        for (Map.Entry<Integer, SeatStatus> seat : source.getSeats().entrySet()) {

            String color;

            switch (seat.getValue()) {

                case DISPONIVEL:
                    color = "VERDE";
                    break;

                case RESERVADO:
                    color = "AMARELO";
                    break;

                default:
                    color = "VERMELHO";
            }

            System.out.println("Assento " + seat.getKey() + " -> " + color);
        }

    }

}

/* Quiosque */

class KioskView implements SeatListener {

    private String name;

    public KioskView(String name) {
        this.name = name;
    }

    @Override
    public void seatChanged(SeatEvent event, BusModel source) {

        System.out.println("\n===== QUIOSQUE " + name + " =====");

        for (Map.Entry<Integer, SeatStatus> seat : source.getSeats().entrySet()) {

            System.out.println(
                    "Assento "
                            + seat.getKey()
                            + " : "
                            + seat.getValue());

        }

    }

}

/* CONTROLLER */

class TicketController {

    private BusModel model;

    public TicketController(BusModel model) {
        this.model = model;
    }

    public void reservarAssento(int seat) {

        System.out.println("\nCliente RESERVOU o assento " + seat);

        model.reserveSeat(seat);
    }

    public void comprarAssento(int seat) {

        System.out.println("\nCliente COMPROU o assento " + seat);

        model.buySeat(seat);
    }

}

/* MAIN (TESTES) */

public class Main {

    public static void main(String[] args) {

        // Model
        BusModel model = new BusModel(6);

        // Views
        CentralPanelView painelCentral = new CentralPanelView();
        KioskView quiosqueA = new KioskView("A");
        KioskView quiosqueB = new KioskView("B");

        // Observers registrados
        model.addListener(painelCentral);
        model.addListener(quiosqueA);
        model.addListener(quiosqueB);

        // Controller
        TicketController controller = new TicketController(model);

        /* TESTES */

        controller.reservarAssento(2);

        controller.comprarAssento(3);

        controller.reservarAssento(5);

        controller.comprarAssento(2);

    }

}
