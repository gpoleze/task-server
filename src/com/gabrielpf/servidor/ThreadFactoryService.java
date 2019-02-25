package com.gabrielpf.servidor;

import java.util.concurrent.ThreadFactory;

public class ThreadFactoryService implements ThreadFactory {
    private static int numero = 1;
    private final ThreadFactory threadFactory;

    public ThreadFactoryService(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    @Override
    public Thread newThread(Runnable r) {


//        Thread thread = new Thread(r, "Thread Servidor Tarefas: " + numero++);
        Thread thread = threadFactory.newThread(r);

        thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
        thread.setDaemon(true);

        return thread;
    }
}
