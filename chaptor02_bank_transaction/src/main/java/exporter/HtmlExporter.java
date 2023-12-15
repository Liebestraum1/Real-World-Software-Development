package exporter;

public class HtmlExporter implements Exporter{

    @Override
    public String export(SummaryStatistics summaryStatistics) {
        String result = "<!DOCTYPE html>\n";
        result += "<html lang=\"ko\">\n";
        result += "<head>";
        result += "<meta charset=\"UTF-8\">\n";
        result += "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n";
        result += "<title>Document</title>\n";
        result += "</head>\n";
        result += "<body>\n";
        result += "거래 금액 총합: " + summaryStatistics.getSum() + "</br>\n";
        result += "거래 금액 평균: " + summaryStatistics.getAverage() + "</br>\n";
        result += "최대 거래 금액: " + summaryStatistics.getMax() + "</br>\n";
        result += "최소 거래 금액: " + summaryStatistics.getMin() + "</br>\n";
        result += "</body>";
        return result;
    }
}
