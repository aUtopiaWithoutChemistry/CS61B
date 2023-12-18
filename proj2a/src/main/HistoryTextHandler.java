package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    NGramMap ngm;
    public HistoryTextHandler(NGramMap ngMap) {
        ngm = ngMap;
    }

    @Override
    public String handle(NgordnetQuery q) {

        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        String response = "";

        for (String word : words) {
            System.out.println(word);
            TimeSeries ts = ngm.weightHistory(word, startYear, endYear);
            response += word;
            response += ": {";
            for (int i = startYear; i < endYear; i++) {
                if (ts.get(i) != null) {
                    response += i + "=" + ts.get(i) + ", ";
                }
                else {
                    continue;
                }
            }
        }
        //System.out.println(response);
        return response;
    }
}
