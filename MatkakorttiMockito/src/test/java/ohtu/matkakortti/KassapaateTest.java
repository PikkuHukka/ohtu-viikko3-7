package ohtu.matkakortti;

import ohtu.matkakortti.Matkakortti;
import ohtu.matkakortti.Kassapaate;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KassapaateTest {

    Kassapaate kassa;
    Matkakortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = mock(Matkakortti.class);
    }

    @Test
    public void kortiltaVelotetaanHintaJosRahaaOn() {
        when(kortti.getSaldo()).thenReturn(10);
        kassa.ostaLounas(kortti);

        verify(kortti, times(1)).getSaldo();
        verify(kortti).osta(eq(Kassapaate.HINTA));
    }

    @Test
    public void kortiltaEiVelotetaJosRahaEiRiita() {
        when(kortti.getSaldo()).thenReturn(4);
        kassa.ostaLounas(kortti);

        verify(kortti, times(1)).getSaldo();
        verify(kortti, times(0)).osta(anyInt());
    }
    //kassapäätteen metodin lataa kutsu lisää matkakortille ladattavan rahamäärän käyttäen kortin metodia lataa jos ladattava summa on positiivinen

    @Test
    public void ladataanRahaaJosPositiivinen() {
        when(kortti.getSaldo()).thenReturn(10);
        kassa.lataa(kortti, 5);

        verify(kortti, times(1)).lataa(anyInt());
    }

    @Test
    public void eiLadataRahaaJosNegatiivinen() {
        when(kortti.getSaldo()).thenReturn(10);
        kassa.lataa(kortti, -5);

        verify(kortti, times(0)).lataa(anyInt());
    }
//kassapäätteen metodin lataa kutsu ei tee matkakortille mitään jos ladattava summa on negatiivinen
}
