package org.arthan.desktop.reversi.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Arthur Shamsiev on 18.10.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class RemoteService {
    public static byte[] checkForUpdates() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/wild/rest/reversi");
        Response response = target.request().get();
        if (response.getStatus() == 200) {
            return readBoardInfo(response);
        } else {
            return null;
        }
    }

    public static byte[] postPlayInfo(byte x, byte y) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/wild/rest/reversi/play");
        byte[] bytes = {x, y};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        Response response = target.request()
                .post(Entity.entity(in, MediaType.APPLICATION_OCTET_STREAM_TYPE));
        return readBoardInfo(response);
    }

    public static byte[] retrieveBoardInfo() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/wild/rest/reversi");
        Response response = target.request().get();
        return readBoardInfo(response);
    }

    private static byte[] readBoardInfo(Response response) {
        InputStream inputStream = response.readEntity(InputStream.class);

        byte[] bytes = new byte[response.getLength()];
        try {
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
