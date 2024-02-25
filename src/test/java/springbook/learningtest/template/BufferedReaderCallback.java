package springbook.learningtest.template;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderCallback {
    Integer doSomeThingWithReader(BufferedReader br) throws IOException;
}
