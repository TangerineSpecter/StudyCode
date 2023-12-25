package com.tangerine.specter.tokenizer;

import org.vosk.LibVosk;
import org.vosk.LogLevel;
import org.vosk.Model;
import org.vosk.Recognizer;

import javax.sound.sampled.AudioSystem;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class VoskDemo {

    public static void main(String[] argv) throws Exception {
        LibVosk.setLogLevel(LogLevel.DEBUG);

        try (Model model = new Model("/Users/zhouliangjun/Downloads/vosk-model/vosk-model-small-cn-0.22/");
             InputStream ais = AudioSystem.getAudioInputStream(
                     new BufferedInputStream(new FileInputStream("/Users/zhouliangjun/Downloads/57zqdk2a.mp3"))
             );
             Recognizer recognizer = new Recognizer(model, 16000)) {

            int nbytes;
            byte[] b = new byte[4096];
            while ((nbytes = ais.read(b)) >= 0) {
                if (recognizer.acceptWaveForm(b, nbytes)) {
                    System.out.println(recognizer.getResult());
                } else {
                    System.out.println(recognizer.getPartialResult());
                }
            }

            System.out.println(recognizer.getFinalResult());
        }
    }
}
