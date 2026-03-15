package org.keirobm.myhome.mediamanager.infrastructure.emuleClient.process;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keirobm.myhome.mediamanager.infrastructure.emuleClient.helper.AmuleProcessHelper;
import org.keirobm.myhome.mediamanager.infrastructure.emuleClient.model.ProcessOutput;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ShowDlProcessTest {

    public static final String TEST_STR = """
F912867BA18B1BA34717F430516B075E Pluribus.S01E04.FRENCH.WEBRip.x264.mp4.torrent
    [0.0%]    0/   2          - Waiting - 001.part.met - Auto [Hi]
26A91F061392639342046C2FC47B37B7 Pluribus.1x01.Noi.Siamo.Noi.ITA.ATVP.WEB-DLRip.x264-UBi.mkv
       [90.6%]   55/  56     (21) - Downloading - 002.part.met - Auto [Lo] - 2.15 MB/s
            """;

    @Mock
    private AmuleProcessHelper processHelper;
    
    @InjectMocks
    private ShowDlProcess sut;

    @Test
    void testExecute() {
        when(this.processHelper.executeCommand(any()))
            .thenReturn(ProcessOutput.builder()
                .exitCode(0)
                .output(TEST_STR)
                .error("")
                .build());

        final var results = this.sut.execute();
        
        assertEquals(2, results.size());
        final var firstResult = results.getFirst();
        assertEquals("F912867BA18B1BA34717F430516B075E", firstResult.getHash());
        assertEquals("Pluribus.S01E04.FRENCH.WEBRip.x264.mp4.torrent", firstResult.getFilename());
        assertEquals(0.0, firstResult.getPercentage());
        assertEquals(0, firstResult.getSeeders());
        assertEquals(2, firstResult.getLeachers());
        assertEquals(0.0, firstResult.getMbSpeed());

        final var secondResult = results.getLast();
        assertEquals("26A91F061392639342046C2FC47B37B7", secondResult.getHash());
        assertEquals("Pluribus.1x01.Noi.Siamo.Noi.ITA.ATVP.WEB-DLRip.x264-UBi.mkv", secondResult.getFilename());
        assertEquals(90.6, secondResult.getPercentage());
        assertEquals(55, secondResult.getSeeders());
        assertEquals(56, secondResult.getLeachers());
        assertEquals(2.15, secondResult.getMbSpeed());

    }
}
