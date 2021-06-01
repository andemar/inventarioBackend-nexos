package com.nexos.inventario.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class UuidUtil {

	/**
	 * Se obtiene el valor menos significativo mediante un random
	 * @return Long
	 */
	private static long get64LeastSignificantBitsForVersion1() {
	    Random random = new Random();
	    long random63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
	    long variant3BitFlag = 0x8000000000000000L;
	    return random63BitLong + variant3BitFlag;
	}

	
	/**
	 * Se obtiene el valor mas significativo calculando los nanosegundos
	 * desde el 15 de oct. de 1582 hasta "Ahora". Luego su valor en bits
	 * se corren 4 a la derecha y se le suma "en bits" el numero de la
	 * version del UUID que se uso (En este caso la version 1 -> 
	 * Cambiando la MAC por un Random).
	 * URL: https://www.baeldung.com/java-uuid#1-version-1
	 * @return Long
	 */
	private static long get64MostSignificantBitsForVersion1() {
	    LocalDateTime start = LocalDateTime.of(1582, 10, 15, 0, 0, 0);
	    Duration duration = Duration.between(start, LocalDateTime.now());
	    long seconds = duration.getSeconds();
	    long nanos = duration.getNano();
	    long timeForUuidIn100Nanos = seconds * 10000000 + nanos * 100;
	    long least12SignificatBitOfTime = (timeForUuidIn100Nanos & 0x000000000000FFFFL) >> 4;
	    long version = 1 << 12;
	    return 
	      (timeForUuidIn100Nanos & 0xFFFFFFFFFFFF0000L) + version + least12SignificatBitOfTime;
	}
	
	
	/**
	 * Genera un uuid
	 * @return String
	 */
	public static String generateUuid() {
		
		long most64SigBits = get64MostSignificantBitsForVersion1();
	    long least64SigBits = get64LeastSignificantBitsForVersion1();

	    UUID uuid = new UUID(most64SigBits, least64SigBits);
		
		return uuid.toString();
	}
	
}