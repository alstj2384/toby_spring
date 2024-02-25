package springbook.learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    public <T> T lineReadTemplate(String filepath, LineCallback<T> callback, T initVal) throws IOException{
        BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader(filepath));
            T res = initVal;
            String line = null;
            while((line = br.readLine()) != null) {
                res = callback.doSomethigWithLine(line, res);
            }
            return res;
        } catch (IOException e){
            throw e;
        } finally {
            if(br != null){
                try{ br.close(); }
                catch(IOException e){System.out.println(e.getMessage());}}
        }
    }

    public Integer calcSum(String filepath) throws IOException{
        LineCallback<Integer> sumCallBack =
                new LineCallback<Integer>() {
                    @Override
                    public Integer doSomethigWithLine(String line, Integer value) {
                        return value + Integer.valueOf(line);
                    }};
        return lineReadTemplate(filepath, sumCallBack, 0);
    }

    public Integer calcMultiply(String filepath) throws IOException {
        LineCallback<Integer> multiplyCallBack =
                new LineCallback<Integer>() {
                    @Override
                    public Integer doSomethigWithLine(String line, Integer value) {
                        return value * Integer.valueOf(line);
                    }};
        return lineReadTemplate(filepath, multiplyCallBack, 1);

    }

    public String calcString(String filepath) throws IOException {
        LineCallback<String> StringCallBack =
                new LineCallback<String>() {
                    @Override
                    public String doSomethigWithLine(String line, String value) {
                        return value+line;
                    }};
        return lineReadTemplate(filepath, StringCallBack, "");

    }

    public Integer fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException{
        BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader(filepath));
            return callback.doSomeThingWithReader(br);
        } catch (IOException e){
            throw e;
        } finally {
            if(br != null){
                try{ br.close(); }
                catch(IOException e){System.out.println(e.getMessage());}}
        }
    }

}
