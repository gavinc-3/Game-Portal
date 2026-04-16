public class County {
    String state;
    String county;
    int trumpVotes;
    int clintonVotes;

    public County(String state, String county, int trumpVotes, int clintonVotes) {
        this.state = state;
        this.county = county;
        this.trumpVotes = trumpVotes;
        this.clintonVotes = clintonVotes;
    }
}