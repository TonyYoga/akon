package ru.job4j.parsersqlru;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserSqlRu {

    private ArrayList<Vacancy> vacancies;
    private Properties properties;
    private static final Logger LOG = LoggerFactory.getLogger(ParserSqlRu.class);
    private StoreData sd;

    public ParserSqlRu(Properties properties) {
        vacancies = new ArrayList<>();
        this.properties = properties;
        sd = new StoreData(properties);
    }

    void parseUrl() {
        String url = properties.getProperty("url.site");
        Document doc;
        boolean isEnough = false;
        int page = 1;
        DateParser dateParser = new DateParser();
        try {
            while (!isEnough) {
                doc = Jsoup.connect(url + page).get();
                String pattern = "java(?!([\\s-]*script))";
                Pattern search = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
                Element table = doc.select("table").get(2);
                Elements rows = table.select("tr");
                isEnough = parseRow(rows, search, dateParser);
                page++;
            }
            //System.out.println(new Date() + " was added " + vacancies.size());
            LOG.info(new Date() + " was added " + vacancies.size());
            if (vacancies.size() > 0) {
                sd.storeData(vacancies);
            }
        } catch (SQLException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private boolean parseRow(Elements rows, Pattern search, DateParser dateParser) throws SQLException{
        StoreData.CheckDate checkDate = sd.checkLastDate();
        long stopDate = checkDate.getLastDate(); // to this value i need to put or start of the year or last date from DB
        boolean isFirstPass = checkDate.isFirstPass();
        boolean isOutDate = false;
        boolean isEnough = false;
        int rowCounter = 1;  //first row is the col names so skip it.
        while (rowCounter < rows.size() && !isOutDate) {
            Element row = rows.get(rowCounter);
            Elements cols = row.select("td");
            Matcher mSearch = search.matcher(cols.get(1).text());
            if (mSearch.find()) {
                long parsedDate = dateParser.getDateFromString(cols.get(5).text());
                Vacancy curVacancy = new Vacancy(cols.get(1).text(), cols.select("a[href]").attr("href"), parsedDate);
                if (parsedDate < stopDate) {
                    isOutDate = true;
                    isEnough = true;
                } else if (isFirstPass) {
                    vacancies.add(curVacancy);
                } else if (!sd.isAdded(curVacancy)) {
                    vacancies.add(curVacancy);
                }
            }
            rowCounter++;
        }
        return isEnough;
    }

}
