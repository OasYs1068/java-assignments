package exercise3;

/**
 * This class defines the "full queue" exception.
 * It is thrown when the client attempts to add an element
 * to a fixed-length queue that is full.
 */
public class FullQueueException extends RuntimeException {

  private Queue queue;

  public FullQueueException(Queue queue) {
    super("The queue is full.");
    this.queue = queue;
  }

  public Queue getQueue() {
    return queue;
  }
}
