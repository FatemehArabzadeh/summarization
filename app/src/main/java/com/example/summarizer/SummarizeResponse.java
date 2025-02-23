package com.example.summarizer;
import java.util.List;

public class SummarizeResponse {
    private List<Candidate> candidates;

    public String getSummary() {
        if (candidates != null && !candidates.isEmpty()) {
            return candidates.get(0).getContent().getParts().get(0).getText();
        }
        return "خطایی در دریافت خلاصه رخ داده است.";
    }

    public static class Candidate {
        private Content content;

        public Content getContent() {
            return content;
        }
    }

    public static class Content {
        private List<Part> parts;

        public List<Part> getParts() {
            return parts;
        }
    }

    public static class Part {
        private String text;

        public String getText() {
            return text;
        }
    }
}
