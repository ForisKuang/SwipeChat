package com.example.kfc36_000.swipechat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.Buffer;

/**
 * Created by hacke on 11/8/2015.
 */
public class ConnectionManager {
    private Socket socket;
    public ConnectionManager() throws UnknownHostException, IOException{
        socket = new Socket("127.0.0.1", 1337);
    }
    public PrintWriter getWriteablePrinter() throws IOException{
        if (socket != null) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("{\"nickname\":\"testnickname\",\"pid\":\"10872942379\",\"type\":\"UPDATE_ONLY\",\"location\":\"LOCATION\",\"message\":\"test message is a test\",\"burstradius\":\"100\",\"rotation\":\"270\"}");
            return writer;
        }else{
            return null;
        }
    }
    public BufferedReader getReadableBufferedReader() throws IOException{
        if (socket != null){
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return reader;
        }else{
            return null;
        }
    }
    public void closeSocket() throws IOException{
        socket.close();
    }
}
