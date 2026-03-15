package org.keirobm.myhome.mediamanager.infrastructure.emuleClient.process;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ParseSearchResultsTest {

    private static final String TEST_STR = """
This is amulecmd 2.3.3

Creating client...
Succeeded! Connection established to aMule 2.3.3
Nr.    Filename:                                                                        Size(MB):  Sources: 
-----------------------------------------------------------------------------------------------------------
0.    Pluribus.1x09.La.chica.o.el.mundo.(Spanish.English.Subs).WEB-DL.1080p.x264-E     4710.243     45
1.    Pluribus 1x01. Nosotros es nosotros [HDRip][Spanish][Antco-ForosFreaky.eu].av     773.297     34
2.    Pluribus - 1x05 La leche @ [h265.1080p].[AC3.192kpbs.Stereo.VO+Spanish].[Subs     946.526     3
3.    Pluribus 1x04. Por favor, Carol [HD-720p][Spanish][Antco-ForosFreaky.eu].mkv      635.629     6
            """;

    private ParseSearchResults sut = new ParseSearchResults();


    @Test
    void testParse() {
        final var results = this.sut.parse(TEST_STR);

        assertEquals(4, results.size());
        final var firstResult = results.getFirst();
        assertEquals("0", firstResult.getChoice());
        assertEquals("Pluribus.1x09.La.chica.o.el.mundo.(Spanish.English.Subs).WEB-DL.1080p.x264-E", firstResult.getFilename());
        assertEquals(4710.243, firstResult.getSize(), 0.001);
        assertEquals(45, firstResult.getSeeds());

        final var secondResult = results.get(1);
        assertEquals("1", secondResult.getChoice());
        assertEquals("Pluribus 1x01. Nosotros es nosotros [HDRip][Spanish][Antco-ForosFreaky.eu].av", secondResult.getFilename());
        assertEquals(773.297, secondResult.getSize(), 0.001);
        assertEquals(34, secondResult.getSeeds());

        final var thirdResult = results.get(2);
        assertEquals("2", thirdResult.getChoice());
        assertEquals("Pluribus - 1x05 La leche @ [h265.1080p].[AC3.192kpbs.Stereo.VO+Spanish].[Subs", thirdResult.getFilename());
        assertEquals(946.526, thirdResult.getSize(), 0.001);
        assertEquals(3, thirdResult.getSeeds());

        final var fourthResult = results.get(3);
        assertEquals("3", fourthResult.getChoice());
        assertEquals("Pluribus 1x04. Por favor, Carol [HD-720p][Spanish][Antco-ForosFreaky.eu].mkv", fourthResult.getFilename());
        assertEquals(635.629, fourthResult.getSize(), 0.001);
        assertEquals(6, fourthResult.getSeeds());
    }
}
