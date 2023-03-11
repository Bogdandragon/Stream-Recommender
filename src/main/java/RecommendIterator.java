import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecommendIterator implements IteratorInterface {
    private List<Stream> streams;
    private int nextIndex;

    public RecommendIterator(User user, int streamType, Map<Integer, InputBase> map) {
        List<Integer> keyList = map.keySet().stream().collect(Collectors.toList());
        keyList.removeIf(k -> !(map.get(k) instanceof Stream) || ((Stream) map.get(k)).getStreamType() != streamType || (user.hasListenedTo((Stream) map.get(k))) || (!user.hasListenedToStreamer((Stream) map.get(k), map)));
        Collections.sort(keyList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (int)(((Stream)map.get(o2)).getNoOfListenings() - ((Stream)map.get(o1)).getNoOfListenings());
            }
        });

        streams = keyList.stream().map(i -> (Stream)map.get(i)).collect(Collectors.toList());
        nextIndex = -1;
        findNextKey();
    }

    @Override
    public boolean hasNext() {
        if(nextIndex >= streams.size())
            return false;
        else return true;
    }

    @Override
    public Stream next() {
        if(!hasNext())
            return null;
        Stream s = streams.get(nextIndex);
        findNextKey();
        return s;
    }

    private void findNextKey() {
        nextIndex++;
    }
}
