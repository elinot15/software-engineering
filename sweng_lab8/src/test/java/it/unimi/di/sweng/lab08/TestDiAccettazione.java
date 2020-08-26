package it.unimi.di.sweng.lab08;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

public class TestDiAccettazione {

    @Before
     public  void setup() {
        Rotator.resetCounter();
    }

    @Test
    public void testNext(){
        Rotator rotator= new Rotator("0 1 2 3 4");
        assertThat(rotator.next()).isEqualTo("0: 0 1 2 3 4");
        assertThat(rotator.next()).isEqualTo("0: 1 2 3 4   0");
        assertThat(rotator.next()).isEqualTo("0: 2 3 4   0 1");
        assertThat(rotator.next()).isEqualTo("0: 3 4   0 1 2");
        assertThat(rotator.next()).isEqualTo("0: 4   0 1 2 3");
    }


    @Test
    public void testhasNext() {
        Rotator rotator = new Rotator("0 1 2 3 4");

        assertThat(rotator.hasNext()).isEqualTo(true);
        rotator.next();

        assertThat(rotator.hasNext()).isEqualTo(true);

        for(int i=0; i<4; i++)
            rotator.next();

        assertThat(rotator.hasNext()).isEqualTo(false);
    }


    @Test
    public void testTwoRotator(){
        Rotator rotator= new Rotator("0 1 2 3 4");
        Rotator rotator1= new Rotator("A B C D E");
        assertThat(rotator.next()).isEqualTo("0: 0 1 2 3 4");
        assertThat(rotator1.next()).isEqualTo("1: A B C D E");
        assertThat(rotator.next()).isEqualTo("0: 1 2 3 4   0");
        assertThat(rotator.next()).isEqualTo("0: 2 3 4   0 1");
        assertThat(rotator1.next()).isEqualTo("1: B C D E   A");
    }

    @Test
    public void testThrowException(){
        Rotator rotator = new Rotator("0 1 2 3 4");
        for (int i = 0; i < 5; i++) {
            rotator.next();
        }
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(()->rotator.next());
    }
}
