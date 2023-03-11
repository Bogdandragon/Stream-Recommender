import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.AbstractMap;
import java.util.Date;
import java.util.TimeZone;

public class Stream extends InputBase {
    private int streamType;

    private String streamerName;
    private int streamGenre;
    private long noOfListenings;
    private int streamerId;
    private long length;
    private long dateAdded;


    public Stream(int streamType, int id, int streamGenre, long noOfListenings, int streamerId, long length, long dateAdded, String name, AbstractMap<Integer, InputBase> inputCollection) {
        super(id, name);
        this.streamType = streamType;
        this.streamGenre = streamGenre;
        this.noOfListenings = noOfListenings;
        this.streamerId = streamerId;
        this.length = length;
        this.dateAdded = dateAdded;
        this.streamerName = inputCollection.get(streamerId).getName();
    }

    public String getStreamerName() {
        return streamerName;
    }

    public long getNoOfListenings() {
        return noOfListenings;
    }

    public String getLength() {
        Duration d = Duration.ofSeconds(length);
        return d.toHours() > 0 ? String.format("%02d:%02d:%02d", d.toHours(), d.toMinutes() - d.toHours() * 60, d.toSeconds() - 60 * d.toMinutes()) :
                String.format("%02d:%02d",d.toMinutes(), d.toSeconds() - 60 * d.toMinutes());
    }

    public String getDateAdded() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(new Date(dateAdded * 1000));
    }

    @JsonIgnore
    public int getStreamerId() {
        return streamerId;
    }

    @JsonIgnore
    public long getDateAddedValue() {
        return dateAdded;
    }

    @JsonIgnore
    public long getStreamType() {
        return streamType;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Duration d = Duration.ofSeconds(length);
        String len = d.toHours() > 0 ? String.format("%02d:%02d:%02d", d.toHours(), d.toMinutes() - d.toHours() * 60, d.toSeconds() - 60 * d.toMinutes()) :
                String.format("%02d:%02d",d.toMinutes(), d.toSeconds() - 60 * d.toMinutes());
        String dateAdd = sdf.format(new Date(dateAdded/1000));
        return String.format("{id:%s,name:%s,streamername:%s,nooflistenings:%s,length:%s,dateadded:%s}", super.getId(), super.getName(), streamerName, noOfListenings, len, dateAdd);
    }

    public void listen() {
        noOfListenings++;
    }
}
