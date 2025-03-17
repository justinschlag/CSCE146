// Justin Schlag 2025
// ProcessScheduler class manages a queue of processes using LLQueue.

public class ProcessScheduler {
    private final LLQueue<Process> processQueue;
    private Process currentProcess;

    public ProcessScheduler() {
        processQueue = new LLQueue<>();
        currentProcess = null;
    }

    public Process getCurrentProcess() {
        return currentProcess;
    }

    public void addProcess(Process newProcess) {
        if (currentProcess == null) {
            currentProcess = newProcess;
        } else {
            processQueue.enqueue(newProcess);
        }
    }

    public void runNextProcess() {
        currentProcess = processQueue.dequeue();
    }

    public void cancelCurrentProcess() {
        if (currentProcess != null) {
            currentProcess = null;
            runNextProcess();
        }
    }

    public void printProcessQueue() {
        processQueue.print();
    }
}
