package com.lukegjpotter.bikeracingireland.service.json;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JsonInputStreamReader {

    public JSONObject readJson(InputStreamReader inputStreamReader) {

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder sb = new StringBuilder();
        String line;

        try {
            while ((line = bufferedReader.readLine()) != null)
                sb.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            return new JSONObject(sb.toString());
        } catch (JSONException e) {
            return null;
        }
    }
}
