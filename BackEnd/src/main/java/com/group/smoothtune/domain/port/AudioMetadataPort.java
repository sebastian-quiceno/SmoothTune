package com.group.smoothtune.domain.port;

import java.io.InputStream;

public interface AudioMetadataPort {
    Float getDuration(InputStream inputStream);
}
