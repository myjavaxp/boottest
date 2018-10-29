package com.example.boottest.service;

import java.io.IOException;

public interface DownloadService {
    byte[] download() throws IOException;
}
