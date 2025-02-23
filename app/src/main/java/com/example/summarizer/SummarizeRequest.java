package com.example.summarizer;
import java.util.Collections;
import java.util.List;

public class SummarizeRequest {
    private List<Content> contents;

    public SummarizeRequest(String text) {
        this.contents = Collections.singletonList(new Content(text));
    }

    public List<Content> getContents() {
        return contents;
    }

    static class Content {
        private List<Part> parts;

        public Content(String text) {
            this.parts = Collections.singletonList(new Part(text));
        }
    }

    static class Part {
        private String text;

        public Part(String text) {
            this.text = text;
        }
    }
}
