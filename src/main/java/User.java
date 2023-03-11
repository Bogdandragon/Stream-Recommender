import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

public class User extends InputBase implements ListStrategy{
    private int id;
    private String name;
    private ArrayList<Integer> streams;

    public User(int id, String name) {
        super(id, name);
        this.streams = new ArrayList<>();
    }

    @Override
    public void list(AbstractMap<Integer, InputBase> inputCollection) {
        ArrayList<Stream> streamObjects = new ArrayList<>();
        for(Integer id: streams) {
            streamObjects.add((Stream)inputCollection.get(id));
        }
        Stream[] s = streamObjects.toArray(new Stream[0]);
        System.out.println(ProiectPOO.JSONOutput(s));
    }

    public void addStream(Integer streamId) {
        this.streams.add(streamId);
    }

    public void listen(Stream stream) {
        if(!streams.contains(stream.getId()))
            streams.add(stream.getId());
    }

    public boolean hasListenedTo(Stream stream) {
        return streams.stream().anyMatch(s -> s == stream.getId());
    }

    public boolean hasListenedToStreamer(Stream stream, Map<Integer, InputBase> map) {
        return streams.stream().anyMatch(s -> stream.getStreamerId() == ((Stream)map.get(s)).getStreamerId());
    }

    public void delete(int parseInt) {
        streams.remove(parseInt);
    }
}
