package com.aularuix.back.monitor;

import org.springframework.scheduling.annotation.Scheduled;

public class LimpiarHorarioMonitor {

	@Scheduled(fixedRate = 3000)
    public void tarea1() {
        System.out.println("Tarea usando fixedRate cada 3 segundos - " + System.currentTimeMillis() / 1000);
    }
}
