package ru.job4j.parsersqlru;

public class Vacancy {
    private String text;
    private String url;
    private long date;

    public Vacancy(String text, String url, long date) {
        this.text = text;
        this.url = url;
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        if (!text.equals(vacancy.text)) {
            return false;
        }
        return url.equals(vacancy.url);
    }

    @Override
    public int hashCode() {
        int result = text.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}
