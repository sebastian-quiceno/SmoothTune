package com.group.smoothtune.infrastructure.storage;

import com.group.smoothtune.domain.exception.ErrorWhileGettingDurationException;
import com.group.smoothtune.domain.port.AudioMetadataPort;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.springframework.stereotype.Service;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;

@Service
public class AudioMetadataImpl implements AudioMetadataPort {

    @Override
    public Float getDuration(InputStream inputStream) {
        try {
            Metadata metadata = new Metadata();
            Mp3Parser parser = new Mp3Parser();

            // Extrae los metadatos sin cargar todo el audio en memoria
            parser.parse(inputStream, new DefaultHandler(), metadata, new ParseContext());

            String durationStr = metadata.get("xmpDM:duration");

            if (durationStr != null) {
                // Parseamos directamente a float para mantener los decimales
                return Float.parseFloat(durationStr);
            }
        } catch (Exception e) {
            // Loguea el error o manéjalo según tu arquitectura
            throw new ErrorWhileGettingDurationException("Ocurrio un error mientras se obtenia la duracion del archivo \n"+e);
        }
        return 0.0f;
    }
}
