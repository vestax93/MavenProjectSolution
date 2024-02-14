package exercise06;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;


import javax.naming.OperationNotSupportedException;
public class AlarmTest {
    private static final double LOW_PRESSURE = 15;
    private static final double NORMAL_PRESSURE = 20;
    private static final double HIGH_PRESSURE = 25;

    @Test
    public void checkIfPressureIsLow(){
        Sensor sensor = Mockito.mock(Sensor.class);
        Alarm alarm = new Alarm(sensor);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(LOW_PRESSURE);
        alarm.check();
        assertTrue(alarm.getAlarmOn());

    }

    @Test
    public void checkIfPressureIsHigh(){
        Sensor sensor = Mockito.mock(Sensor.class);
        Alarm alarm = new Alarm(sensor);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(HIGH_PRESSURE);
        alarm.check();
        assertTrue(alarm.getAlarmOn());

    }

    @Test
    public void checkIfPressureIsNormal(){
        Sensor sensor = Mockito.mock(Sensor.class);
        Alarm alarm = new Alarm(sensor);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(NORMAL_PRESSURE);
        alarm.check();
        assertFalse(alarm.getAlarmOn());

    }

}