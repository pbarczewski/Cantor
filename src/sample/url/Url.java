package sample.url;

public enum Url {
    DATA_URL("http://data.fixer.io/api/latest?access_key=2bf705629be2bb04ec5ca9460c074037&base=");
    private String url;

    Url(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
