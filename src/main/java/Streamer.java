import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Streamer extends InputBase implements ListStrategy {
    private int streamerType;

    public Streamer(int streamerType, int id, String name) {
        super(id, name);
        this.streamerType = streamerType;
    }

    @Override
    public void list(AbstractMap<Integer, InputBase> inputCollection) {
        ArrayList<Stream> streamObjects = new ArrayList<>();
        Collection<InputBase> inputs = inputCollection.values();
        for(int k = inputs.size() - 1; k >= 0; k--) {
            InputBase i = (InputBase) inputs.toArray()[k];
            if(i instanceof Stream && ((Stream)i).getStreamerId() == super.getId())
                streamObjects.add((Stream)i);
        }
        Stream[] s = streamObjects.toArray(new Stream[0]);
        System.out.println(ProiectPOO.JSONOutput(s));
    }
}
